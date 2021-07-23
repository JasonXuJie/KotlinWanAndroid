package debug

import javax.inject.Inject

data class Person constructor(val name: String, val age: Int){

    @Inject
    constructor() : this("vivo", 18)
}
