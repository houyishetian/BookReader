package lin.com.bookreader.view;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;

import lin.com.bookreader.R;

/**
 * By cindy on 12/22/14 3:53 PM.
 */
public class CircularProgressView extends View {

    private static final Interpolator ANGLE_INTERPOLATOR = new LinearInterpolator();
    private static final Interpolator SWEEP_INTERPOLATOR = new AccelerateDecelerateInterpolator();

    private final int angleAnimatorDuration;

    private final int sweepAnimatorDuration;

    private final int minSweepAngle;

    private final float mBorderWidth;

    private final RectF fBounds = new RectF();
    private ObjectAnimator mObjectAnimatorSweep;
    private ObjectAnimator mObjectAnimatorAngle;
    private boolean mModeAppearing = true;
    private final Paint mPaint;
    private float mCurrentGlobalAngleOffset;
    private float mCurrentGlobalAngle;

    private float mCurrentSweepAngle;
    private boolean mRunning;
    private final int mColor;

    public CircularProgressView(Context context) {
        this(context, null);
    }

    public CircularProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircularProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(
                attrs,
                R.styleable.CircularProgressView,
                defStyleAttr, 0);

        mBorderWidth = a.getDimension(
                R.styleable.CircularProgressView_borderWidth,
                getResources().getDimension(R.dimen.circular_default_border_width));

        angleAnimatorDuration = a.getInt(
                R.styleable.CircularProgressView_angleAnimationDurationMillis,
                getResources().getInteger(R.integer.circular_default_angleAnimationDurationMillis));

        sweepAnimatorDuration = a.getInt(
                R.styleable.CircularProgressView_sweepAnimationDurationMillis,
                getResources().getInteger(R.integer.circular_default_sweepAnimationDuration));

        minSweepAngle = a.getInt(
                R.styleable.CircularProgressView_minSweepAngle,
                getResources().getInteger(R.integer.circular_default_miniSweepAngle));

        int colorArrayId = a.getResourceId(R.styleable.CircularProgressView_progressColor,
                R.color.circular_green);
        if (isInEditMode()) {
            mColor = getResources().getColor(R.color.circular_green, null);
        } else {
            mColor = getResources().getColor(colorArrayId, null);
        }
        a.recycle();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Cap.ROUND);
        mPaint.setStrokeWidth(mBorderWidth);
        mPaint.setColor(mColor);

        setupAnimations();
    }

    private void innerStart() {
        if (isRunning()) {
            return;
        }
        mRunning = true;
        mObjectAnimatorAngle.start();
        mObjectAnimatorSweep.start();
        invalidate();
    }

    private void innerStop() {
        if (!isRunning()) {
            return;
        }
        mRunning = false;
        mObjectAnimatorAngle.cancel();
        mObjectAnimatorSweep.cancel();
        invalidate();
    }

    private boolean isRunning() {
        return mRunning;
    }

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (visibility == VISIBLE) {
            innerStart();
        } else {
            innerStop();
        }
    }

    @Override
    protected void onAttachedToWindow() {
        innerStart();
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        innerStop();
        super.onDetachedFromWindow();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        fBounds.left = mBorderWidth / 2f + .5f;
        fBounds.right = w - mBorderWidth / 2f - .5f;
        fBounds.top = mBorderWidth / 2f + .5f;
        fBounds.bottom = h - mBorderWidth / 2f - .5f;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        float startAngle = mCurrentGlobalAngle - mCurrentGlobalAngleOffset;
        float sweepAngle = mCurrentSweepAngle;
        if (mModeAppearing) {
            mPaint.setColor(mColor);
            sweepAngle += minSweepAngle;
        } else {
            startAngle = startAngle + sweepAngle;
            sweepAngle = 360 - sweepAngle - minSweepAngle;
        }
        canvas.drawArc(fBounds, startAngle, sweepAngle, false, mPaint);
    }

    private void toggleAppearingMode() {
        mModeAppearing = !mModeAppearing;
        if (mModeAppearing) {
            mCurrentGlobalAngleOffset = (mCurrentGlobalAngleOffset + minSweepAngle * 2) % 360;
        }
    }

    private final Property<CircularProgressView, Float> mAngleProperty = new Property<CircularProgressView, Float>(Float.class, "angle") {
        @Override
        public Float get(CircularProgressView object) {
            return object.getCurrentGlobalAngle();
        }

        @Override
        public void set(CircularProgressView object, Float value) {
            object.setCurrentGlobalAngle(value);
        }
    };

    private final Property<CircularProgressView, Float> mSweepProperty = new Property<CircularProgressView, Float>(Float.class, "arc") {
        @Override
        public Float get(CircularProgressView object) {
            return object.getCurrentSweepAngle();
        }

        @Override
        public void set(CircularProgressView object, Float value) {
            object.setCurrentSweepAngle(value);
        }
    };

    private void setupAnimations() {
        mObjectAnimatorAngle = ObjectAnimator.ofFloat(this, mAngleProperty, 360f);
        mObjectAnimatorAngle.setInterpolator(ANGLE_INTERPOLATOR);
        mObjectAnimatorAngle.setDuration(angleAnimatorDuration);
        mObjectAnimatorAngle.setRepeatMode(ValueAnimator.RESTART);
        mObjectAnimatorAngle.setRepeatCount(ValueAnimator.INFINITE);

        mObjectAnimatorSweep = ObjectAnimator.ofFloat(this, mSweepProperty, 360f - minSweepAngle * 2);
        mObjectAnimatorSweep.setInterpolator(SWEEP_INTERPOLATOR);
        mObjectAnimatorSweep.setDuration(sweepAnimatorDuration);
        mObjectAnimatorSweep.setRepeatMode(ValueAnimator.RESTART);
        mObjectAnimatorSweep.setRepeatCount(ValueAnimator.INFINITE);
        mObjectAnimatorSweep.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                toggleAppearingMode();
            }
        });
    }

    public void setCurrentGlobalAngle(float currentGlobalAngle) {
        mCurrentGlobalAngle = currentGlobalAngle;
        invalidate();
    }

    public float getCurrentGlobalAngle() {
        return mCurrentGlobalAngle;
    }

    public void setCurrentSweepAngle(float currentSweepAngle) {
        mCurrentSweepAngle = currentSweepAngle;
        invalidate();
    }

    public float getCurrentSweepAngle() {
        return mCurrentSweepAngle;
    }
}
