package com.example.domain

interface UseCase

interface IOUseCase<in Request, out Response> : UseCase {
    suspend fun execute(request: Request): Response
}

suspend fun <Response> IOUseCase<Unit, Response>.execute() = execute(Unit)