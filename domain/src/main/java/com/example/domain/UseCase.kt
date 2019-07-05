package com.example.domain

interface UseCase<in Request, out Response> {
    suspend fun execute(request: Request): Response
}

suspend fun <Response> UseCase<Unit, Response>.execute() = execute(Unit)