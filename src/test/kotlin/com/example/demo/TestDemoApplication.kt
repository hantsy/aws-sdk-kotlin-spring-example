package com.example.demo

import org.springframework.boot.fromApplication
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.with

@TestConfiguration(proxyBeanMethods = false)
class TestDemoApplication

fun main(args: Array<String>) {
	fromApplication<DemoApplication>().with(TestDemoApplication::class).run(*args)
}
