package com.example.exoticworld.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exoticworld.data.repository.AnimalitosRepository
import kotlinx.coroutines.flow.MutableStateFlow
import com.example.exoticworld.data.model.Animalitos
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel (
private val repoAnim : AnimalitosRepository = AnimalitosRepository()
): ViewModel(){

    private val _animalitos = MutableStateFlow<List<Animalitos>>(emptyList()) //Contenedor que cambia con el tiempo(?

    val animalitos: StateFlow<List<Animalitos>> = _animalitos.asStateFlow() //esto transforma StateFlow inmutable, para que se vea pero no se modifique
    // se supone que así separamos de mutable e inmutable para que nunca se puedan tocar estos datos desde la UI o eso entendí
    // y así cada vez que _animalitos.value cambie, la UI que lo observe se actualizará automáticamente.

    init{
        viewModelScope.launch {
            _animalitos.value = repoAnim.getAll()
        }
    }

    fun getAnimalito(id : Int) : Animalitos? = repoAnim.getById(id)
}