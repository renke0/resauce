package com.renke.resauce.client;

import com.renke.resauce.api.mojang.MojangApi;

@Client(url = "${resauce.mojang.url}", name = "mojangClient")
public interface MojangClient extends MojangApi {}
