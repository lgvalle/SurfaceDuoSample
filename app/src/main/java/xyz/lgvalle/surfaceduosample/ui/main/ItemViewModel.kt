package xyz.lgvalle.surfaceduosample.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.io.Serializable

class ItemViewModel : ViewModel() {
    private val _selectedItem: MutableLiveData<Item?> = MutableLiveData()
    val selectedItem: LiveData<Item?> = _selectedItem

    private val _items = MutableLiveData<List<Item>>().apply { postValue(ITEMS) }
    val items: LiveData<List<Item>> = _items

    fun selectItem(item: Item): Item? {
        return ITEMS
            .find { it == item }
            ?.also { _selectedItem.postValue(it) }
    }

    fun deselectAny() {
        _selectedItem.postValue(null)
    }

    companion object {
        val ITEMS = listOf(
            Item("Item 1", "This is the first item"),
            Item("Item 2", "This is the second item"),
            Item("Item 3", "This is the third item")
        )
    }
}

data class Item(val id: String, val content: String) : Serializable
