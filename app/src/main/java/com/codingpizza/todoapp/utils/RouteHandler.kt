package com.codingpizza.todoapp.utils

fun <T> generateRoute(route : String, paramKey: String, param : T) : String =
    route.replace("{$paramKey}","$param")