package com.jeongdaeri.viewmodeltest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*


// 액티비티는 값의 변경을 뷰모델이 가지고 있는 라이브 데이터를 통해 전달받는다
class MainActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val TAG: String = "로그"
    }


    // 나중에 값이 설정될거라고 lateinit 으로 설정
    lateinit var myNumberViewModel: MyNumberViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 뷰 모델 프로바이더를 통해 뷰모델 가져오기
        // 라이프사이클을 가지고 있는 녀석을 넣어줌 즉 자기 자신
        // 우리가 가져오고 싶은 뷰모델 클래스를 넣어서 뷰모델을 가져오기
        myNumberViewModel = ViewModelProvider(this).get(MyNumberViewModel::class.java)

        // 뷰모델이 가지고 있는 값의 변경사항을 관찰할 수 있는 라이브 데이터를 옵저빙한다
        myNumberViewModel.currentValue.observe(this, Observer {
            Log.d(TAG, "MainActivity - myNumberViewModel - currentValue 라이브 데이터 값 변경 : $it")

            number_textview.text = it.toString()
        })

        // 리스너 연결
        plus_btn.setOnClickListener(this)
        minus_btn.setOnClickListener(this)

    }

    // 버튼클릭시
    override fun onClick(view: View?) {
        val userInput = number_input_edittext.text.toString().toInt()

        // 뷰모델에 라이브데이터 값을 변경하는 메소드 실행
        when (view){
            plus_btn ->
                myNumberViewModel.updateValue(actionType = NumberActionType.PLUS, userInput)
            minus_btn ->
                myNumberViewModel.updateValue(actionType = NumberActionType.MINUS, userInput)
        }
    }

}
