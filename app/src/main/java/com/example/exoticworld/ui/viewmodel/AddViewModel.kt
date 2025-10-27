package com.example.exoticworld.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.exoticworld.data.model.FormAddState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class AddViewModel : ViewModel() {
    // StateFlow privado para mutar estado

    private val _formState = MutableStateFlow(FormAddState())

    // StateFlow público expuesto a la UI (solo lectura)
    val formState: StateFlow<FormAddState> = _formState

    // manejo de cambios en nombre
    fun onNombreChange(value: String){
        _formState.update { state ->
            val error = when {
                value.isBlank() -> "El nombre de usuario no puede estar vacio"
                value.length < 5 -> "El nombre de usuario debe tener al menos 5 caracteres"
                else -> null
            }
            state.copy(
                nombre = value,
                nombreError = error,
                isValid = validateForm(
                    state.copy(
                        nombre = value,
                        nombreError = error
                    )
                )
            )
        }
    }

    // manejo de cambios en "correoUsuario"
    fun onCorreoChange(value: String) {
        _formState.update { state ->
            val error = when {
                value.isBlank() -> "El correo no puede estar vacío"
                !value.contains("@") -> "Debe llevar @"
                else -> null
            }
            state.copy(
                correoUsuario = value,
                correoUsuarioError = error,
                isValid = validateForm(
                    state.copy(
                        correoUsuario = value,
                        correoUsuarioError = error
                    )
                )
            )
        }
    }

    // manejo de cambios en "contrasena"
    fun onContrasenaChange(value: String){
        _formState.update { state ->
            val error = when {
                value.isBlank() -> "La contraseña no puede estar vacía"
                value.length < 6 -> "La contraseña debe contener al menos 6 caracteres"
                else -> null
            }
            state.copy(
                contrasena = value,
                contrasenaError = error,
                isValid = validateForm(state.copy(contrasena = value, contrasenaError = error))
            )

        }
    }


    fun emailValidation (value : String){
       //exRegex
    }
    fun onConfirmarContrasenaChange(value: String){
        _formState.update { state ->
            val error = when {
                value.isBlank() -> "Debe confirmar la contraseña"
                value!= state.contrasena -> "Las contraseñas no coinciden"
                else -> null
            }
            state.copy(
                confirmarContrasena  = value,
                confirmarContrasenaError = error,
                isValid = validateForm(state.copy(confirmarContrasena = value, confirmarContrasenaError = error))
            )

        }
    }


    //validacion del formulario
    private fun validateForm(state: FormAddState): Boolean {
        return  state.nombreError == null &&
                state.correoUsuarioError == null &&
                state.contrasenaError == null &&
                state.confirmarContrasenaError == null &&
                state.nombre.isNotBlank() &&
                state.correoUsuario.isNotBlank() &&
                state.contrasena.isNotBlank() &&
                state.confirmarContrasena.isNotBlank()
    }

    fun onSubmit() {
        _formState.value = FormAddState() // vacia el formulario

    }



}
