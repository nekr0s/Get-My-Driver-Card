package com.example.nekr0s.get_my_driver_card.views.list.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nekr0s.get_my_driver_card.R;
import com.example.nekr0s.get_my_driver_card.models.Request;

import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomRecyclerView extends RecyclerView.Adapter<CustomRecyclerView.ItemViewHolder> {

    private List<Request> mRequests;
    private OnItemClickListener mOnRequestClickListener;

    public CustomRecyclerView(List<Request> mRequests) {
        this.mRequests = mRequests;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {
        itemViewHolder.setOnItemClickListener(mOnRequestClickListener);
        itemViewHolder.bind(mRequests.get(i));
    }

    @Override
    public int getItemCount() {
        return mRequests.size();
    }

    public void setOnRequestClickListener(OnItemClickListener onRequestClickListener) {
        this.mOnRequestClickListener = onRequestClickListener;
    }

    public void sortByStatus() {
        mRequests.sort(Comparator.comparingInt(o -> o.getStatus().getNum()));
        notifyDataSetChanged();
    }

//    public void sortByDate() {
//        Collections.sort(datestring, new Comparator<String>() {
//            DateFormat f = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
//
//            @Override
//            public int compare(String o1, String o2) {
//                try {
//                    return f.parse(o1).compareTo(f.parse(o2));
//                } catch (ParseException e) {
//                    throw new IllegalArgumentException(e);
//                }
//            }
//        });
//    }

    protected static class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.request_id)
        TextView mId;

        @BindView(R.id.request_type)
        TextView mType;

        @BindView(R.id.request_status)
        TextView mStatus;

        @BindView(R.id.status_color)
        ImageView mStatusColor;

        private Request mRequest;
        private OnItemClickListener mOnClickListener;

        ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Request request) {
            mRequest = request;

            mId.setText(String.valueOf(mRequest.getRequestId()));
            mType.setText(mRequest.getRequestType());
            mStatus.setText(mRequest.getStatusString());
            mStatusColor.setColorFilter(mRequest.color());
        }

        @OnClick
        public void onClick() {
            mOnClickListener.onClick(mRequest);
        }

        private void setOnItemClickListener(OnItemClickListener onClickListener) {
            mOnClickListener = onClickListener;
        }

    }

    public interface OnItemClickListener {
        void onClick(Request request);
    }
}
