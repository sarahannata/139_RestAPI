package com.example.pampraktikum8.repository

import com.example.pampraktikum8.model.Kontak
import com.example.pampraktikum8.service_api.KontakService
import java.io.IOException

interface KontakRepository {
    suspend fun getKontak(): List<Kontak>

    suspend fun insertKontak(kontak: Kontak)

    suspend fun updateKontak(Id: Int, kontak: Kontak)

    suspend fun deleteKontak(Id: Int)

    suspend fun getKontakById(Id: Int): Kontak
}

class NetworkKontakRepository(
    private val kontakApiService: KontakService
) : KontakRepository {
    override suspend fun getKontak(): List<Kontak> =
        kontakApiService.getKontak()

    override suspend fun insertKontak(kontak: Kontak){
        kontakApiService.insertKontak(kontak)
    }

    override suspend fun updateKontak(id: Int, kontak: Kontak){
        kontakApiService.updateKontak(id, kontak)
    }

    override suspend fun deleteKontak(id: Int) {
        try{
            val response = kontakApiService.deleteKontak(id)
            if (response.isSuccessful){
                throw IOException("Failed to delete kontak, HTTP status code: " +
                " ${response.code()}")
            }
            else{
                response.message()
                println(response.message())
            }
        }
        catch (e: Exception){
            throw e
        }
    }

    override suspend fun getKontakById(id: Int): Kontak {
        return kontakApiService.getKontakById(id)
    }
}
