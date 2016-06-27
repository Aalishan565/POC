package com.poc;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FirstFragment.OnCardSelectedListener {
    MyPagerAdapter mFragmentPagerAdapter;


    private CardDetailDTO cardDetailDTO;
    private List<CardDetailDTO> mDataList = null;
    private ViewPager vpPager;
    private EditText mEtCardNumber;
    private EditText mEtExpirationDate;
    private EditText mEtCvv;
    ArrayList mCardNumber = new ArrayList<String>();
    ArrayList mDate = new ArrayList<String>();
    ArrayList mCvv = new ArrayList<String>();
    static int currentPos = -1;
    private ArrayList mPosition = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card);
        mEtCardNumber = (EditText) findViewById(R.id.et_card_number_credit_card);
        mEtExpirationDate = (EditText) findViewById(R.id.et_expiration_date_credit_card);
        mEtCvv = (EditText) findViewById(R.id.et_cvv_number_credit_card);
        //firstFragment = FirstFragment.newInstance();



        mDataList = new ArrayList<CardDetailDTO>();
        for (int i = 0; i < 20; i++) {
            addCardNumber(i);
            addDate(i);
            addCVV(i);
            setPositon(i);

            cardDetailDTO = new CardDetailDTO();
            cardDetailDTO.setCardNumber(mCardNumber.get(i).toString());
            cardDetailDTO.setExpirationDate(mDate.get(i).toString());
            cardDetailDTO.setCvv(mCvv.get(i).toString());
            cardDetailDTO.setPos((Integer) mPosition.get(i));
            mDataList.add(cardDetailDTO);


        }
        List<Fragment> fragments = getFragments();
        vpPager = (ViewPager) findViewById(R.id.viewpager);
        mFragmentPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), fragments);

        vpPager.setAdapter(mFragmentPagerAdapter);
        vpPager.setClipToPadding(false);
        vpPager.setPageMargin(20);
        vpPager.setOffscreenPageLimit(20);

        vpPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int position) {

            }

            // This method will be invoked when the current page is scrolled
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Code goes here
            }

            // Called when the scroll state changes:
            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
            @Override
            public void onPageScrollStateChanged(int state) {
                // Code goes here
            }
        });        /*vpPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "page clicked", Toast.LENGTH_LONG).show();
            }
        });*/
    }

    private void setPositon(int j) {
        mPosition.add(j);
    }

    private void addCVV(int i) {
        mCvv.add("45" + i);
    }

    private void addCardNumber(int num) {

        mCardNumber.add("xxxx xxxx xxxx 123" + num);


    }

    private void addDate(int num) {

        mDate.add("11/2" + num);


    }

    private List<Fragment> getFragments() {

        List<Fragment> fList = new ArrayList<Fragment>();
        for (int i = 0; i < 20; i++) {
            fList.add(FirstFragment.newInstance(mDataList.get(i)));
        }


        return fList;

    }


    @Override
    public void onCardSelected(CardDetailDTO mCardDetailDTO, int position) {
        vpPager.setSelected(true);

        vpPager.getAdapter().notifyDataSetChanged();

        vpPager.setCurrentItem(position,true);
     /*   if (currentPos == -1) {
            currentPos = position;
        }
        else {
            mFragmentPagerAdapter.getItem(currentPos).getView().findViewById(R.id.ll_tick).setVisibility(View.GONE);
        }*/
        mEtCardNumber.setText(mCardDetailDTO.getCardNumber());
        mEtExpirationDate.setText(mCardDetailDTO.getExpirationDate());
        mEtCvv.setText(mCardDetailDTO.getCvv());
    }
}
