package com.example.nekr0s.get_my_driver_card.views.create.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nekr0s.get_my_driver_card.R;
import com.example.nekr0s.get_my_driver_card.models.Reason;
import com.example.nekr0s.get_my_driver_card.utils.enums.RequestReason;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReasonsAdapter extends BaseAdapter {

    private Activity activity;
    private List<Reason> reasons;
    private LayoutInflater inflater;


    public ReasonsAdapter(Activity activity) {
        this.activity = activity;
        initReasons();
        inflater = activity.getLayoutInflater();
    }

    public ReasonsAdapter(Activity activity, List<Reason> reasons) {
        this.activity = activity;
        this.reasons = reasons;
        inflater = activity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return reasons.size();
    }

    @Override
    public Reason getItem(int position) {
        return reasons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.reasons_item, viewGroup, false);

            holder = new ViewHolder();

            holder.tvReasonName = view.findViewById(R.id.tv_reason_name);
            holder.ivCheckBox = view.findViewById(R.id.iv_check_box);

            view.setTag(holder);
        } else holder = (ViewHolder) view.getTag();

        Reason reason = reasons.get(position);
        holder.tvReasonName.setText(reason.getRequestReasonString());
        if (reason.isSelected()) holder.ivCheckBox.setBackgroundResource(R.drawable.checked);
        else holder.ivCheckBox.setBackgroundResource(R.drawable.check);
        return view;
    }

    public void update(int position, Reason reason) {
        reasons.set(position, reason);
        notifyDataSetChanged();
    }


    private void initReasons() {
        reasons = new ArrayList<>(Arrays.asList(
                new Reason(false, RequestReason.REASON_STOLEN, activity.getString(R.string.card_has_been_stolen)),
                new Reason(false, RequestReason.REASON_LOST, activity.getString(R.string.lost_my_card)),
                new Reason(false, RequestReason.REASON_MALFUNCTIONING, activity.getString(R.string.card_is_malfunctioning)),
                new Reason(false, RequestReason.REASON_DAMAGED, activity.getString(R.string.card_has_been_damaged)),
                new Reason(false, RequestReason.REASON_ADDRESS_CHANGE, activity.getString(R.string.change_my_address)),
                new Reason(false, RequestReason.REASON_NAME_CHANGE, activity.getString(R.string.change_my_name)),
                new Reason(false, RequestReason.REASON_PHOTO_CHANGE, activity.getString(R.string.change_my_photo))))
        ;
    }

    class ViewHolder {
        TextView tvReasonName;
        ImageView ivCheckBox;
    }
}
