package com.smilegateblog.smliegateblog.domain.usecase.scrap

import android.util.Log
import androidx.paging.PagingData
import com.smilegateblog.smliegateblog.data.dto.scrap.GetScrapPostItem
import com.smilegateblog.smliegateblog.domain.repository.ScrapRepository
import com.smilegateblog.smliegateblog.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetScrapPostUseCase @Inject constructor(private val scrapRepository: ScrapRepository){
    operator fun invoke() : Flow<PagingData<GetScrapPostItem>> {
        Log.d("get scrap Post", "usecase exec")

        return scrapRepository.getScrapPost()
    }
}