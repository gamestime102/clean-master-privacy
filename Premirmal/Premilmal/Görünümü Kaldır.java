    }
    
    void onMove(final float x, final float y) {
        if (shouldBeResponsive) {
            final int xTransformed = (int) Math.abs(x * 100 / (mButton.getContext().getResources().getDisplayMetrics().widthPixels / 2));
            final int bottomPadding = buttonBottomPadding - (xTransformed / 5);
            if (x < 0) {
                mButton.setPadding(0, 0, xTransformed, bottomPadding);
            } else {
                mButton.setPadding(xTransformed, 0, 0, bottomPadding);
            }
        }
    }

    void destroy() {
        if (mLayout != null && mLayout.getParent() != null) {
            mWindowManager.removeView(mLayout);
        }
        mLayout = null;
        mWindowManager = null;
    }

    private void addToWindow(View layout) {
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                        WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                PixelFormat.TRANSLUCENT
        );
        mWindowManager.addView(layout, params);
    }
}
