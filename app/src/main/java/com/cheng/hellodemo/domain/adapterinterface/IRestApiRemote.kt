package com.cheng.hellodemo.domain.adapterinterface

interface IRestApiRemote {
    suspend fun get(url: String, headers: Map<String, String> = emptyMap()): Result<String>
}
