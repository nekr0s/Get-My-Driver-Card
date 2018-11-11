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
import com.example.nekr0s.get_my_driver_card.utils.enums.RequestStatus;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RequestsAdapter extends RecyclerView.Adapter<RequestsAdapter.ItemViewHolder> {

    private List<Request> mRequests;
    private OnItemClickListener mOnRequestClickListener;

    public RequestsAdapter() {
        this.mRequests = new ArrayList<>();
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

    // TODO: Needs fixing
    public void sortByStatus() {
        mRequests.sort(Comparator.comparing(Request::getRequestStatus));
        notifyDataSetChanged();
    }

    public void sortByType() {
        mRequests.sort(Comparator.comparing(Request::getRequestStatus));
        notifyDataSetChanged();
    }

    public void sortByDate() {
        mRequests.sort(Comparator.comparing(Request::getRequestId));
        notifyDataSetChanged();
    }

    public boolean isEmpty() {
        return mRequests.isEmpty();
    }

    public void clear() {
        mRequests.clear();
    }

    public void addAll(List<Request> requests) {
        mRequests.addAll(requests);
    }

    public void add(Request request) {
        mRequests.add(request);
        notifyDataSetChanged();
    }

    public void updateStatus(long requestId, RequestStatus requestStatus) {
        for (Request request : mRequests) {
            if (request.getRequestId() == requestId) {
                request.setRequestStatus(requestStatus);
            }
        }
        notifyDataSetChanged();
    }

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

            mId.setText("Request ID: " + String.valueOf(mRequest.getRequestId()));
            mType.setText("Type: " + mRequest.getRequestTypeString());
            mStatus.setText("Status: " + mRequest.getStatusString());
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
