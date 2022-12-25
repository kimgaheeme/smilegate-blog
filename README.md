## Smilegate winter devCamp 개인 프로젝트 -Blog

---

스마일게이트 윈터 데브 캠프를 위한 개인 프로젝트 Blog입니다.

현재 1차 목표(기본적인 기능 수행)을 약 90%정도 달성한 상태이며, 추가로 2주간 테스트 코드와 오류를 처리할 예정입니다.



### 개발기간

---

2022.12.10 - 2022.12.11 - 앱 디자인 및 데이터 베이스 설계

2022.12.12 - 2022.12.23 - fast api를 이용한 서버 개발 및, AWS를 이용한 배포

2022.12.13 - 2022.12.16, 2022.12.23 - android 1차 개발 종료




### 목표와 그에따른 달성 수준

---

- AOS
 
    <details>
     <aside>
    💡 Jetpack compose를 이용하여 뷰를 짠 경험만 있고 xml을 이용한 경험 없음 → **xml을 이용하여 코드 작성완수할 것**, 코드 재사용성을 높이는 방법 찾아보고 적용해보기(커스텀뷰 개발 경험)
    
    </aside>
    
    ⇒ xml을 이용하여 코드를 작성했으며 코드 재사용성을 높이기 위해 Adapter를 재사용할 수 있도록함, 하지만 커스텀뷰 개발은 부족
    
    <aside>
    💡 Life Cycle에 대한 이해 부족 → **Life Cycle과 상태 변경에 따른 처리 방식을 고려하여 코드 작성**하고, 그 경험을 따로 기록하여 다음 개발때 시간 낭비를 줄이기
    
    </aside>
    
    ⇒ Life Cycle을 고려하여 설계하려 했고, 코드에 그런부분이 어느정도 넣긴 하였지만 자신의 것으로 만들지 못함.
    
    - 참고 코드
        
        ```kotlin
        private fun observeProduct() {
                viewModel.postDetail.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                    .onEach { product ->
                        product?.let { setupPostDetail(it) }
                    }
                    .launchIn(lifecycleScope)
            }
        ```
        
    
    <aside>
    💡 Coroutine(Flow..)에 대한 이해 부족 → **코드를 작성할 때 왜 이러한 메소드를 사용했는지, 다른 방안은 없었는지 매번 고려할 것**, 특히 Flow는 메소드가 많아 아직 쓰임을 잘 구별을 못하는데  이러한 부분을 채우기 위해 매 코드에 이유를 설명할 것.
    
    </aside>
    
    ⇒ stateflow와 flow의 사용 경우를 구별하여 사용했고, 왜 그렇게 사용했는지도 설명할 수 있었다. 특히 viewmodel에서의 stateflow의 필요성인 데이터 홀더의 역할에 대해 알게되었다. 하지만 그 외 conflate나 flatMapConcat과 같은 부분은 학습이 부족하다.
    
    <aside>
    💡 테스트 코드 작성경험 없음 → **커버리지 40% 이상 달성**하여 테스크 코드 작성 경험을 얻을 것, 아직 해보지 않아서 생기는 두려움을 없애는 것이 목표
    
    </aside>
    
    ⇒ 테스트 코드를 작성하지 못하였다. 2차 개발때 채워볼 예정이다.
    
    <aside>
    💡 **UX, 애니메이션, 인터랙션부분 강화**하기 → 1. 구글에서 제공하는 기본적인 애니메이션 사용해보고 사용자에게 더 나은 경험을 줄 수 있도록 하기, 2. 커스텀 애니메이션 만들고 **도구화하여 올릴것** ( 너무 복잡한 애니메이션 보다는 간단한 애니메이션을 위주로 )
    
    </aside>
    
    ⇒ 이것 역시도 달성하지 못하였다. 2차개발때 화면 전환을 위주로 추가할 예정이다.
    <div markdown="1">
    
- Server(Django → fast api)
    
    <details>
    <aside>
    💡 서버 개발 경험 없음 → MySQL을 이용하여 DB를 설계한 경험은 있지만 아키텍쳐를 설계해 본 경험이 없기 때문에 이와 관련된 경험 쌓기, 구조, 흐름파악
    
    </aside>
    
    ⇒ 처음 서버를 개발하며 DB를 제대로 설계하는 것의 필요성을 느낄 수 있었음. 특히 이미지를 저장하는 방식이나, 글 수정과 같은 부분에서 초기 서버 설계를 잘못하여 많은 시간을 허비한 경험으로 인해 초기에 서버 설계를 소비자의 사용 Flow를 생각해 가며 설계해야함을 알게됨. 
    
    <aside>
    💡 서버 첫 개발이라 서버와 관련하여 성능, 기능을 늘리기 보단 **서버와 관련된 단어, 흐름, 구조를 익혀서 백엔드와 협업할 때 의사소통을 더욱 원활하게 할 것**
    
    </aside>
    
    ⇒ AWS를 이용하여 배포해보고 그 과정에서 알게된 용어들이나 흐름을 알게 되면서 앞으로 프로젝트에 도움이 될 것같음.
    <div markdown="1">
    
    
- 공통
    
   <details>
    <aside>
    💡 코드 스멜 줄이기 → 깔끔한 코드 수정을 나중으로 미루지 않기, 코드를 작성할 수록 더 나은 코드가 될 것, 소나큐브 사용법 익히기 “일단 굴러는 가네” 라는 생각 하지않기,,,
    
    </aside>
    
    ⇒ 코드 스멜을 줄이지 못했고 소나 큐브 사용을 하지 못함.
    
    <aside>
    💡 **트러블 슈팅 문서화 하기** → 오류가 나게된 상황, 해결방안, 고민해볼것을 문서화 할 것
    
    </aside>
    
    ⇒ 문서화 했지만 깔끔하지 않고 오류 + 대처법 정도만 작성, 후에 수정할 예정
    
    <aside>
    💡 개발 요구사항을 세세하게 문서화 시켜서 개발에 혼선을 빚지 않도록 하기
    
    </aside>
    
    ⇒ 작성 완료
   <div markdown="1">
    
    
    

### App 미리보기

---



### 아키텍쳐

---

<img width="497" alt="스크린샷 2022-12-25 오후 9 58 19" src="https://user-images.githubusercontent.com/77616888/209469167-ff93a881-e2fb-4bc1-9039-6135aaa0f950.png">

### 데이터 베이스 설계 과정및 요구사항 정리

---
<img width="968" alt="스크린샷 2022-12-12 오전 1 15 38" src="https://user-images.githubusercontent.com/77616888/209469150-ea543ab0-ba05-46f5-bb6d-ceb3ae96217d.png">




### 사용 기술

---

- AOS

mvvm, clean architecture, Dagger-Hilt, Flow, Kotlin

- Server

Fast api, SQLAlchemy, Mysql, AWS(RDS, S3)

### 구현 시 발생한 문제

---

1. multipartfile를 이용한 Post방식에서의 오류

이미지 전송을 위하여 multipart를 사용해야 했습니다, 또한 이전에 했던 프로젝트(spring)에서는 이미지 이외의 파일은 json으로 변환시켜서 보냈었는데 fast api에서는 json과 File이 함께 전송이 안되는건지, 아님 제 실수인건지 이미지 이외의 파일이 아예 전송이 되지 않는 오류를 만나게 되었고 해결하지 못한채로 Part가 아니라 Query로 데이터를 보내게 되었습니다. 또한 이로인해 이미지가 Optional인 경우는 Multipart로 데이터를 보내지 못하게 되어 수정기능에서 이미지를 올리지 않으면 오류가 발생하게 되었습니다.

1. jetpack paging3 라이브러리에서 item추가, 삭제

paging3 라이브러리를 사용하다 보니 불러온 paging data에 따로 데이터를 추가하거나 삭제할 수 없었습니다. 이에 매번 댓글이나 글을 작성할 때마다 새로고침을 해야하는 문제가 있었습니다.




### 확인받고 싶은 부분

---

1. clean architecture와 관련한 파일 구조 및 코드
    
    clean architecture를 사용하였을때 아래 코드처럼 use case를 작성하는게 맞는지, 그리고 api를 통해 응답받은 코드를 repository에서 처리하는것인지 usecase에서 처리하는 것인지 궁금합니다. 또한 
    
2. viewmodel과 activity, fragment 부분 코드
    
    이전 프로젝트에서는 state를 첫번째와 같이 작성했었는데 이번에는 두번째 코드와 같이 작성했습니다. 둘 중 뭐가 더 좋을지 고민을 했었고 이와 관련하여 state를 handle하는 부분도 부족한 점은 없는지 궁금합니다.
    
    ```kotlin
    private data class HomeViewModelState(
        val postsFeed: PostsFeed? = null,
        val selectedPostId: String? = null, // TODO back selectedPostId in a SavedStateHandle
        val isArticleOpen: Boolean = false,
        val favorites: Set<String> = emptySet(),
        val isLoading: Boolean = false,
        val errorMessages: List<ErrorMessage> = emptyList(),
        val searchInput: String = "",
    ) {
    ```
    
    ```kotlin
    sealed class PostDetailActivityState {
        object Init : PostDetailActivityState()
        data class IsLoading(val isLoading: Boolean) : PostDetailActivityState()
        data class ShowToast(val message: String) : PostDetailActivityState()
        data class SuccessAddComment(val commentId: Int) : PostDetailActivityState()
        data class IsUpdateComment(val isUpdateComment: Boolean, var commentId: Int) : PostDetailActivityState()
    }
    ```
    
    
    

### 궁금한 부분

---

1. clean architecture를 실무에서도 사용하나요? clean architecture의 경우 멀티 모듈로 사용한다고 알고있는데, 모두 멀티모듈을 사용하는건지 아니면 제 프로젝트처럼 파일을 나누기도 하는건지, 실무에서는 어떤지 알고 싶습니다.
2. jetpack compose를 많이 사용하나요? 아니면 도입을 고려하거나 하는 경우가 많나요? jetpack compose를 이용한 개발을 많이해봤고 xml이 부족한 상황에서 다음 프로젝트를 compose로 진행할지, xml로 진행할지 정하기 위해 상황을 알고 싶습니다.
3. paging3라이브러리를 많이 사용하나요? 그렇지 않다면 그에대한 방안은 무엇이 있는지 궁금합니다.
