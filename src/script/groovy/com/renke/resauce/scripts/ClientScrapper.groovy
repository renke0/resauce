package com.renke.resauce.scripts

import groovy.json.JsonSlurper
import java.nio.file.Files
import java.security.MessageDigest
import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import org.yaml.snakeyaml.Yaml

println 'Updating game resources...'
Map config = new Yaml().load(new File(getClass().getResource('/scrappers.yaml').toURI()).text)
jarFiles(config).each { fileInfo ->
  def zip = new ZipFile(fileInfo.jar)
  zip.entries()
    .asIterator()
    .each { entry ->
      if (entry.name.startsWithAny('assets/minecraft/recipes/', 'data/minecraft/recipes/')) {
        save(this.args[0], "${fileInfo.id}/recipes", zip, entry)
      } else if (entry.name.startsWithAny('assets/minecraft/loot_tables/', 'data/minecraft/loot_tables/')) {
        save(this.args[0], "${fileInfo.id}/loot_tables", zip, entry)
      } else if (entry.name.startsWithAny('assets/minecraft/textures/items/', 'assets/minecraft/textures/item/')) {
        save(this.args[0], "${fileInfo.id}/textures", zip, entry)
      }
    }
}
println "Game resources imported successfully"

static save(String resourcePath, String path, ZipFile zip, ZipEntry entry) {
  def fileName = entry.name.substring(entry.name.lastIndexOf('/'))
  def location = new File("$resourcePath/game-data/$path/")
  if (!location.exists()) {
    location.mkdirs()
  }
  def file = new File(location, fileName)
  if (file.exists()) {
    file.delete()
  }
  Files.copy(zip.getInputStream(entry), file.toPath())
}

static jarFiles(Map config) {
  def tempDir = new File("${System.getProperty('java.io.tmpdir')}/com.renke.resauce")
  if (!tempDir.exists()) {
    tempDir.mkdirs()
  }
  return config.versions.collect { Map version ->
    Map<String, Map> metadata = new JsonSlurper().parse(new URL(version.'metadata-url'))
    Map clientInfo = metadata.downloads.client
    def clientFile = new File(tempDir, "client-${version.id}.jar")
    if (!clientFile.exists() || !checksum(clientFile, clientInfo.sha1)) {
      downloadFile(clientInfo.url, clientFile)
    }
    return [id: version.id, jar: clientFile]
  }
}

static checksum(File file, String sha1) {
  def digest = MessageDigest.getInstance("SHA1")
  def inputStream = file.newInputStream()
  def buffer = new byte[16384]
  def len
  while ((len = inputStream.read(buffer)) > 0) {
    digest.update(buffer, 0, len)
  }
  def sha1sum = digest.digest()
  def result = ""
  for (byte b : sha1sum) {
    result += toHex(b)
  }
  return result == sha1
}

static toHex(int b) {
  return hexChr((b & 0xF0) >> 4) + hexChr(b & 0x0F)
}

static hexChr(int b) {
  return Integer.toHexString(b & 0xF)
}

static downloadFile(String url, File target) {
  print "Downloading file $url"
  new URL(url).openConnection().with { connection ->
    target.withOutputStream { out ->
      connection.inputStream.with { input ->
        out << input
        input.close()
      }
    }
  }
  println "..........Done"
}
