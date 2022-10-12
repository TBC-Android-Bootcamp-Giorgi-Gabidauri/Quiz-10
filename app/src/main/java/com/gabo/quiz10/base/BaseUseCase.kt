package com.gabo.quiz10.base

interface BaseUseCase<Params, Result> {
    suspend operator fun invoke(params: Params): Result
}