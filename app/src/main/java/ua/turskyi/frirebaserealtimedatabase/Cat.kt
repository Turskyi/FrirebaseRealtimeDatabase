package ua.turskyi.frirebaserealtimedatabase

import com.google.firebase.database.IgnoreExtraProperties


@IgnoreExtraProperties
class Cat {
    var catName: String? = null
    var age = 0

    constructor() { // Конструктор по умолчанию для DataSnapshot.getValue(Cat.class)
    }

    constructor(name: String?, age: Int) {
        catName = name
        this.age = age
    }
}