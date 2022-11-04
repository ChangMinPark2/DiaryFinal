package com.example.diaryproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRvDiary;              // 리사이클러 뷰 (리스트 뷰)
    DiaryListAdapter mAdapter;          // 리사이클러 뷰와 연동할 어댑터
    ArrayList<DiaryModel> mLstDiary;    // 리스트에 표현할 다이어리 데이터들 (배열)
    DataBaseHelper mDataBaseHelper; //데이터 베이스 헬퍼 클래스

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 데이터 베이스 갹체의 초기화
        mDataBaseHelper = new DataBaseHelper(this);

        mLstDiary = new ArrayList<>();

        mRvDiary = findViewById(R.id.rv_diary);

        mAdapter = new DiaryListAdapter();          // 리사이클러 뷰 어댑터 인스탠스 생성

        // 다이어리 샘플 아이템 2개 생성
//        DiaryModel item = new DiaryModel();
//        item.setId(0);
//        item.setTitle("캡스톤 디자인 프로젝트 예제");
//        item.setContent("내 내용입니다.");
//        item.setUserDate("2022/10/18 Tue");
//        item.setWriteDate("2022/10/18 Tue");
//        item.setWeatherType(0);
//        mLstDiary.add(item);
//
//        DiaryModel item2 = new DiaryModel();
//        item2.setId(0);
//        item2.setTitle("캡스톤 디자인 중간 점검");
//        item2.setContent("아무렴 어때");
//        item2.setUserDate("2022/10/17 Mon");
//        item2.setWriteDate("2022/10/17 Mon");
//        item2.setWeatherType(3);
//        mLstDiary.add(item2);

        //mAdapter.setSampleList(mLstDiary);
        mRvDiary.setAdapter(mAdapter);

        // 액티비티 (화면) 이 실행될 때 가장 먼저 호출되는 곳
        FloatingActionButton floatingActionButton = findViewById(R.id.btn_write);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 작성하기 버튼을 누를 때 호출되는 곳

                // 작성하기 화면으로 이동!
                Intent intent = new Intent(MainActivity.this, DiaryDetailActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        //액티비티의 재개
        //get load list
        setLoadRecentList();
    }

    private void setLoadRecentList(){
        //최근 데이터 베이스 정보를 가지고와서 리사이클러뷰에 갱신해준다.

        //이전에 배열 리스트에 저장된 데이터가 있으면 비워버린다.
        if (!mLstDiary.isEmpty()){
            mLstDiary.clear();
        }

        mLstDiary = mDataBaseHelper.getDiaryListFromDB(); // 데이터베이스로부터 저장되어 있는 DB를 확인하여 가지고 온다.
        mAdapter.setListInit(mLstDiary);
    }
}