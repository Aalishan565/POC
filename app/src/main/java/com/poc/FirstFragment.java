package com.poc;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

/**
 * Created by aalishan on 12/1/16.
 */
public class FirstFragment extends Fragment {

    TextView mTvLblVisaCard;
    TextView mTvLVisaCardNo;
    TextView mTvExpirationDate;
    LinearLayout mLlTick;
    private CardDetailDTO mCardDetailDTO = new CardDetailDTO();
    private myCustomOnlickListener clickListener;
    OnCardSelectedListener mCallback;
    private static int selectedPos = -1;

/*
    public static FirstFragment newInstance() {
        FirstFragment fragment = new FirstFragment();
        return fragment;
    }*/

    public interface OnCardSelectedListener {
        public void onCardSelected(CardDetailDTO mCardDetailDTO,int selectedPos);
    }

    public static final FirstFragment newInstance(CardDetailDTO message) {
        FirstFragment f = new FirstFragment();
        Bundle bdl = new Bundle();
        bdl.putSerializable("data", message);
        f.setArguments(bdl);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_pager_item, container, false);
        mTvLblVisaCard = (TextView) view.findViewById(R.id.tv_lbl_visa_credit_card);
        mTvLVisaCardNo = (TextView) view.findViewById(R.id.tv_credit_card_number);
        mTvExpirationDate = (TextView) view.findViewById(R.id.tv_expiration_date);
        mLlTick = (LinearLayout) view.findViewById(R.id.ll_tick);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mCardDetailDTO = (CardDetailDTO) bundle.getSerializable("data");
            // for (int i = 0; i < mCardDetailDTO.size(); i++) {
            mTvExpirationDate.setText(mCardDetailDTO.getExpirationDate().toString());
            mTvLVisaCardNo.setText(mCardDetailDTO.getCardNumber().toString());
          /*  if(mCardDetailDTO.getPos()==selectedPos)
                setTick(true);
            else
                setTick(false);*/
            //}
        }
        view.setOnClickListener(new myCustomOnlickListener(mCardDetailDTO));
        return view;
    }

    private class myCustomOnlickListener implements View.OnClickListener {

        private CardDetailDTO cardDetailDTO;
        private final int selPos;

        public myCustomOnlickListener(CardDetailDTO message) {
            this.cardDetailDTO = message;
            this.selPos=cardDetailDTO.getPos();
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(getActivity(),
                    "Selected Card Number: " + cardDetailDTO.getCardNumber(), Toast.LENGTH_SHORT).show();

            mCallback.onCardSelected(cardDetailDTO, selPos);
            selectedPos=selPos;
            setTick(true);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnCardSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    public void setTick(boolean set) {
        if (set)
            mLlTick.setVisibility(View.VISIBLE);
        else
            mLlTick.setVisibility(View.GONE);
    }

}