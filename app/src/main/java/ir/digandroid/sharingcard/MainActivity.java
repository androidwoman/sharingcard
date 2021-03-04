package ir.digandroid.sharingcard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;

import android.animation.Animator;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

        ImageButton socialIcon;
        AppCompatImageView coverImage;
        LinearLayout layoutBtns;
        RelativeLayout layoutReveal;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            socialIcon = findViewById(R.id.social_icon);
            layoutBtns = (LinearLayout) findViewById(R.id.layout_btns);
            layoutReveal = (RelativeLayout) findViewById(R.id.layout_reveal);
            coverImage = (AppCompatImageView) findViewById(R.id.cover_image);
            socialIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean isAppeared = (layoutReveal.getVisibility() == View.VISIBLE);

                    int centerX = (socialIcon.getRight() + socialIcon.getLeft()) / 2;
                    int centerY = (socialIcon.getBottom() + socialIcon.getTop()) / 2;
                    float radius = (float) Math.hypot(centerX - coverImage.getLeft(), coverImage.getHeight());
                    if(isAppeared){
                        disappear(centerX, centerY, radius);
                        socialIcon.setImageResource(R.drawable.twitter_logo);
                        socialIcon.setBackgroundResource(R.drawable.social_icon_bg);
                    } else {
                        appear(centerX, centerY, radius);
                        socialIcon.setImageResource(R.drawable.image_cancel);
                        socialIcon.setBackgroundResource(R.drawable.social_icon_bg);
                    }
                }
            });
        }

        private void appear(int centerX, int centerY, float radius) {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                Animator animator = ViewAnimationUtils.
                        createCircularReveal(layoutReveal, centerX, centerY, 0, radius);
                animator.setDuration(700);
                animator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {
                        layoutBtns.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        Animation fadein = AnimationUtils
                                .loadAnimation(getApplicationContext(), R.anim.fadein);
                        layoutBtns.startAnimation(fadein);
                        layoutBtns.setVisibility(View.VISIBLE);

                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
                layoutReveal.setVisibility(View.VISIBLE);
                animator.start();

            } else{
                Animation fadein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);
                fadein.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        layoutBtns.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Animation anim = AnimationUtils
                                .loadAnimation(getApplicationContext(), R.anim.fadein);
                        layoutBtns.setVisibility(View.VISIBLE);
                        layoutBtns.startAnimation(anim);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                layoutReveal.setVisibility(View.VISIBLE);
                layoutReveal.startAnimation(fadein);
            }

        }

        private void disappear(int centerX, int centerY, float radius){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                Animator animator = ViewAnimationUtils.
                        createCircularReveal(layoutReveal, centerX, centerY, radius, 0);
                animator.setDuration(500);
                animator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        layoutReveal.setVisibility(View.GONE);
                        layoutBtns.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
                animator.start();

            } else {
                Animation fadeout = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadeout);
                fadeout.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        layoutBtns.setVisibility(View.GONE);
                        layoutReveal.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                layoutReveal.startAnimation(fadeout);
            }

        }

    }
