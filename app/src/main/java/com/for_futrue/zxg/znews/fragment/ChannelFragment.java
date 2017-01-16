package com.for_futrue.zxg.znews.fragment;

import android.app.Activity;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.for_futrue.zxg.znews.R;
import com.for_futrue.zxg.znews.adapter.DragAdapter;
import com.for_futrue.zxg.znews.adapter.OtherDragAdapter;
import com.for_futrue.zxg.znews.bean.Channel;
import com.for_futrue.zxg.znews.presenter.ChannelPresenter;
import com.for_futrue.zxg.znews.view.ChannelFragmentUi;
import com.for_futrue.zxg.znews.view.DragGrid;
import com.for_futrue.zxg.znews.view.OtherGridView;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;

import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

/**
 * Created by zxg on 2017/1/3.
 */
public class ChannelFragment extends BaseFragment<ChannelPresenter,ChannelFragmentUi> implements ChannelFragmentUi,AdapterView.OnItemClickListener {


    private List<Channel> userChannelList;
    private List<Channel> otherChannelList;


    private DragGrid userDragGrid;


    private OtherGridView otherDragGrid;

    private DragAdapter userAdapter;
    private OtherDragAdapter otherAdapter;
    private ImageButton operateButton;
    private boolean isOperate = false;
    /** 是否在移动，由于这边是动画结束后才进行的数据更替，设置这个限制为了避免操作太频繁造成的数据错乱。 */
    boolean isMove = false;
    private Activity mContext;
    @Override
    ChannelPresenter createPresenter() {

        return new ChannelPresenter();
    }

    @Override
    ChannelFragmentUi getUi() {
        return this;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        Log.i("zxg5","getactivity:"+getActivity());
        mContext = getActivity();
        getmPresenter().setContext(mContext);

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.channel_fragment,null);
        userDragGrid = (DragGrid)view.findViewById(R.id.userGridView);
        otherDragGrid = (OtherGridView)view.findViewById(R.id.otherGridView);



        initData();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        operateButton = (ImageButton)getActivity().findViewById(R.id.operate_button);
        operateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("zxg", "operate onclick");
                if (isOperate) {
                    dealOperate();
                } else {
                    getActivity().onBackPressed();
                }
            }
        });


    }



    private void initData(){
        userChannelList = getmPresenter().getChannel(ChannelPresenter.USER_CHANNEL);
        otherChannelList = getmPresenter().getChannel(ChannelPresenter.OTHER_CHANNEL);
        userAdapter = new DragAdapter(mContext,userChannelList);
        otherAdapter = new OtherDragAdapter(mContext,otherChannelList);
        userDragGrid.setAdapter(userAdapter);
        otherDragGrid.setAdapter(otherAdapter);
        userDragGrid.setOnItemClickListener(this);
        otherDragGrid.setOnItemClickListener(this);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
        isOperate = true;
        if(isMove){
            return;
        }
        switch (parent.getId()){
            case R.id.userGridView:
                //position为 0，1 的不可以进行任何操作
                if (position != 0 && position != 1) {
                    final ImageView moveImageView = getView(view);
                    if (moveImageView != null) {
                        TextView newTextView = (TextView) view.findViewById(R.id.text_item);
                        final int[] startLocation = new int[2];
                        newTextView.getLocationInWindow(startLocation);
                        final Channel channel = ((DragAdapter) parent.getAdapter()).getItem(position);//获取点击的频道内容
                        otherAdapter.setVisible(false);
                        //添加到最后一个
                        otherAdapter.addItem(channel);
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                try {
                                    int[] endLocation = new int[2];
                                    //获取终点的坐标
                                    otherDragGrid.getChildAt(otherDragGrid.getLastVisiblePosition()).getLocationInWindow(endLocation);
                                    MoveAnim(moveImageView, startLocation , endLocation, channel,userDragGrid);
                                    userAdapter.setRemove(position);
                                } catch (Exception localException) {
                                }
                            }
                        }, 50L);
                    }
                }
                break;
            case R.id.otherGridView:
                final ImageView moveImageView = getView(view);
                if (moveImageView != null){
                    TextView newTextView = (TextView) view.findViewById(R.id.text_item);
                    final int[] startLocation = new int[2];
                    newTextView.getLocationInWindow(startLocation);
                    final Channel channel = ((OtherDragAdapter) parent.getAdapter()).getItem(position);
                    userAdapter.setVisible(false);
                    //添加到最后一个
                    userAdapter.addItem(channel);
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            try {
                                int[] endLocation = new int[2];
                                //获取终点的坐标
                                userDragGrid.getChildAt(userDragGrid.getLastVisiblePosition()).getLocationInWindow(endLocation);
                                MoveAnim(moveImageView, startLocation , endLocation, channel,otherDragGrid);
                                otherAdapter.setRemove(position);
                            } catch (Exception localException) {
                            }
                        }
                    }, 50L);
                }
                break;
            default:
                break;
        }
    }
    /**
     * 点击ITEM移动动画
     * @param moveView
     * @param startLocation
     * @param endLocation
     * @param moveChannel
     * @param clickGridView
     */
    private void MoveAnim(View moveView, int[] startLocation,int[] endLocation, final Channel moveChannel,
                          final GridView clickGridView) {
        int[] initLocation = new int[2];
        //获取传递过来的VIEW的坐标
        moveView.getLocationInWindow(initLocation);
        //得到要移动的VIEW,并放入对应的容器中
        final ViewGroup moveViewGroup = getMoveViewGroup();
        final View mMoveView = getMoveView(moveViewGroup, moveView, initLocation);
        //创建移动动画
        TranslateAnimation moveAnimation = new TranslateAnimation(
                startLocation[0], endLocation[0], startLocation[1],
                endLocation[1]);
        moveAnimation.setDuration(300L);//动画时间
        //动画配置
        AnimationSet moveAnimationSet = new AnimationSet(true);
        moveAnimationSet.setFillAfter(false);//动画效果执行完毕后，View对象不保留在终止的位置
        moveAnimationSet.addAnimation(moveAnimation);
        mMoveView.startAnimation(moveAnimationSet);
        moveAnimationSet.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                isMove = true;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                moveViewGroup.removeView(mMoveView);
                // instanceof 方法判断2边实例是不是一样，判断点击的是DragGrid还是OtherGridView
                if (clickGridView instanceof DragGrid) {
                    otherAdapter.setVisible(true);
                    otherAdapter.notifyDataSetChanged();
                    userAdapter.remove();
                } else {
                    userAdapter.setVisible(true);
                    userAdapter.notifyDataSetChanged();
                    otherAdapter.remove();
                }
                isMove = false;
            }
        });
    }

    /**
     * 获取移动的VIEW，放入对应ViewGroup布局容器
     * @param viewGroup
     * @param view
     * @param initLocation
     * @return
     */
    private View getMoveView(ViewGroup viewGroup, View view, int[] initLocation) {
        int x = initLocation[0];
        int y = initLocation[1];
        viewGroup.addView(view);
        LinearLayout.LayoutParams mLayoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        mLayoutParams.leftMargin = x;
        mLayoutParams.topMargin = y;
        view.setLayoutParams(mLayoutParams);
        return view;
    }

    /**
     * 创建移动的ITEM对应的ViewGroup布局容器
     */
    private ViewGroup getMoveViewGroup() {
        ViewGroup moveViewGroup = (ViewGroup) mContext.getWindow().getDecorView();
        LinearLayout moveLinearLayout = new LinearLayout(mContext);
        moveLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        moveViewGroup.addView(moveLinearLayout);
        return moveLinearLayout;
    }
    private ImageView getView(View view) {
        view.destroyDrawingCache();
        view.setDrawingCacheEnabled(true);
        Bitmap cache = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        ImageView iv = new ImageView(getActivity());
        iv.setImageBitmap(cache);
        return iv;
    }

    public List<Channel> getUserChannelList(){
        return userAdapter.channelList;
    }
    public List<Channel> getOtherChannelList(){
        return otherAdapter.channelList;
    }


    private void dealOperate(){
        Log.i("zxg","dealOpreate");
        if(isOperate){
            getmPresenter().saveChannel();
            isOperate = false;
            getmPresenter().onChannelUpdate();
            Toast.makeText(mContext,"已更新频道",Toast.LENGTH_SHORT).show();
        }
        getActivity().onBackPressed();
    }
}
