package com.vrushabh.quotehive.data.repository

import com.vrushabh.quotehive.data.api.QuotesAPI
import com.vrushabh.quotehive.data.models.QuoteListItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class QuoteRepository @Inject constructor(private val quotesAPI: QuotesAPI) {

    private val _quotes = MutableStateFlow<List<QuoteListItem>>(emptyList())
    val quotes: StateFlow<List<QuoteListItem>>
        get() = _quotes

    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories: StateFlow<List<String>>
        get() = _categories

    suspend fun getQuotes(category: String) {
        val result = quotesAPI.getQuotes("tweets[?(@.category==\"$category\")]")
        if (result.isSuccessful && result.body() != null) {
            _quotes.emit(result.body()!!)
        }
    }

    suspend fun getCategories() {
        val result = quotesAPI.getCategories()
        if (result.isSuccessful && result.body() != null) {
            _categories.emit(result.body()!!)
        }
    }

}