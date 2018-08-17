package vsl.bhavesh.mvpdemo.presenter

import vsl.bhavesh.mvpdemo.beans.IncExtBean

interface IncExcPresenterAPI {

    fun addInput(bean: IncExtBean)
    fun readInput()
}