package com.renke.resauce.scripts

import groovy.json.JsonBuilder
import org.jsoup.Jsoup
import org.yaml.snakeyaml.Yaml

println 'Updating item list...'

Map config = new Yaml().load(new File(getClass().getResource('/scrappers.yaml').toURI()).text)

config.versions.each { Map version ->
  def doc = Jsoup.connect(version.'items-url').get()
  def itemTable = doc.select('#minecraft_items')
  def items = itemTable.select('tr')
    .findAll { line -> line.select('th').empty }
    .collect { line ->
      [
        id: line.select('td:nth-child(2)>em').text(),
        name: line.select('td:nth-child(2)>a').text()
      ]
    }
  println "Found $items.size items for version $version.id"

  def resourceDir = new File("${this.args[0]}/game-data/$version.id")
  resourceDir.mkdirs()

  def itemsFile = new File(resourceDir, 'items.json')
  itemsFile.createNewFile()
  itemsFile.write(new JsonBuilder(items).toPrettyString())
}

println 'Items imported successfully'
