package com.ramcosta.destinations.sample

import com.ramcosta.composedestinations.annotation.NavGraph

@MainNavGraph
@NavGraph
annotation class AccountNavGraph(
    val start: Boolean = false
)