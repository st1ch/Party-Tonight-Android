package app.media.opp.partytonight.presentation.utils;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by piekie (Artem Vasylenko)
 * on 11/2/16
 */

public final class AnimationDrawableUtil {

    @Nullable
    public static AnimationDrawable configureAnimation(ViewGroup container,
                                                       int enterFadeDuration, int exitFadeDuration) {

        AnimationDrawable animationDrawable = null;

        if (container != null) {
            animationDrawable = (AnimationDrawable) container.getBackground();

            animationDrawable.setEnterFadeDuration(enterFadeDuration);
            animationDrawable.setExitFadeDuration(exitFadeDuration);
        }

        return animationDrawable;
    }

    public static void setAnimationFrame(AnimationDrawable animation, int frame) {
        if (frame < animation.getNumberOfFrames()) {

            for (int i = 0; i < frame + 1; i++) {
                animation.run();
            }
        }
    }

    public static void resetAnimation(AnimationDrawable animation, View parent, int resId) {
        animation.stop();

        parent.setBackground(null);

        parent.setBackgroundResource(resId);
    }

    /**
     * Starts background animation
     * Need to be called in onResume
     */
    public static void startGradientAnimation(AnimationDrawable animationDrawable) {
        if (animationDrawable != null && !animationDrawable.isRunning()) {
            animationDrawable.start();
        }
    }

    /**
     * Stops background animation
     * Need to be called in onPause
     */
    public static void stopGradientAnimation(AnimationDrawable animationDrawable) {
        if (animationDrawable != null && animationDrawable.isRunning()) {
            animationDrawable.stop();
        }
    }

    public static int getCurrentFrame(AnimationDrawable animationDrawable) {
        // The variable that will guard the frame number
        int frameNumber = 0;

        // Get the frame of the animation
        Drawable currentFrame, checkFrame;
        currentFrame = animationDrawable.getCurrent();

        // Checks the position of the frame
        for (int i = 0; i < animationDrawable.getNumberOfFrames(); i++) {
            checkFrame = animationDrawable.getFrame(i);
            if (checkFrame == currentFrame) {
                frameNumber = i;
                break;
            }
        }

        return frameNumber;
    }
}
