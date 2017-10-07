package com.itdeveapps.gamebooster.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.VectorDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itdeveapps.gamebooster.R;
import com.itdeveapps.gamebooster.model.AppInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Omar AlTamimi on 6/4/2017.
 */

public class AppAdapter extends RecyclerView.Adapter<AppAdapter.ViewHolder> {

    private List<AppInfo> appInfoList;
    private Context context;
    private ClickListener mClicklistener;

    public AppAdapter(List<AppInfo> appInfoList, Context context, ClickListener listener) {
        this.appInfoList = appInfoList;
        this.context = context;
        this.mClicklistener = listener;
    }

    @Override
    public AppAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_app, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AppAdapter.ViewHolder holder, int position) {
        final AppInfo appInfo = appInfoList.get(position);
        holder.appName.setText(appInfo.getName());
        if (appInfo.getIcon() instanceof BitmapDrawable) {
            Bitmap bitmap = ((BitmapDrawable) appInfo.getIcon()).getBitmap();
            float density = this.context.getResources().getDisplayMetrics().density;
            holder.appImage.setImageDrawable(new BitmapDrawable(this.context.getResources()
                    , Bitmap.createScaledBitmap(bitmap
                    , (int) (48.0f * density), (int) (48.0f * density), true)));
        } else if (appInfo.getIcon() instanceof VectorDrawable) {
            Log.e("love", "onBindViewHolder: " + appInfo.getName() + "" + appInfo.getPackageName());
            Bitmap bitmap = Bitmap.createBitmap(appInfo.getIcon().getIntrinsicWidth(),
                    appInfo.getIcon().getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            appInfo.getIcon().setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            appInfo.getIcon().draw(canvas);
            float density = this.context.getResources().getDisplayMetrics().density;
            holder.appImage.setImageDrawable(new BitmapDrawable(this.context.getResources()
                    , Bitmap.createScaledBitmap(bitmap
                    , (int) (48.0f * density), (int) (48.0f * density), true)));

        }
    }

    @Override
    public int getItemCount() {
        return appInfoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.appIcon)
        ImageView appImage;
        @BindView(R.id.appName)
        TextView appName;
        @BindView(R.id.appItem)
        LinearLayout appItem;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            appItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mClicklistener.onClick(appInfoList.get(getAdapterPosition()));
        }
    }

    public interface ClickListener {
        void onClick(AppInfo appInfo);
    }
}