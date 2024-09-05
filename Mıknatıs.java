        mLayoutParams.y += deltaY;
        if (mRemoveView != null && shouldFlingAway) {
            mRemoveView.onMove(mLayoutParams.x, mLayoutParams.y);
        }
        mWindowManager.updateViewLayout(mIconView, mLayoutParams);
        if (mListener != null) {
            mListener.onMove(mLayoutParams.x, mLayoutParams.y);
        }
        if (shouldFlingAway && !isBeingDragged && Math.abs(mLayoutParams.x) < 50
                && Math.abs(mLayoutParams.y - (mContext.getResources().getDisplayMetrics().heightPixels / 2)) < 250) {
            flingAway();
        }
    }

    public void destroy() {
        mWindowManager.removeView(mIconView);
        if (mRemoveView != null) {
            mRemoveView.destroy();
        }
        if (mListener != null) {
            mListener.onIconDestroyed();
        }
        mContext = null;
    }

    private class MoveAnimator implements Runnable {

        private Handler handler = new Handler(Looper.getMainLooper());
        private float destinationX;
        private float destinationY;
        private long startingTime;

        private void start(float x, float y) {
            this.destinationX = x;
            this.destinationY = y;
            startingTime = System.currentTimeMillis();
            handler.post(this);
        }

        @Override
        public void run() {
            if (mIconView != null && mIconView.getParent() != null) {
                float progress = Math.min(1, (System.currentTimeMillis() - startingTime) / 400f);
                float deltaX = (destinationX - mLayoutParams.x) * progress;
                float deltaY = (destinationY - mLayoutParams.y) * progress;
                move(deltaX, deltaY);
                if (progress < 1) {
                    handler.post(this);
                }
            }
        }

        private void stop() {
            handler.removeCallbacks(this);
        }

    }
}
