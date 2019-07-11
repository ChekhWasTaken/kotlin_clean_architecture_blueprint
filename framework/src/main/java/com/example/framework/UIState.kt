package com.example.framework

sealed class UIState<Result> {
    class Loading<Result> : UIState<Result>()
    class Error<Result>(val ex: Throwable) : UIState<Result>()
    class Success<Result>(val data: Result) : UIState<Result>()
}