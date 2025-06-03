package com.paysnug.tappa;

import android.util.Log;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.mba.tappa.Tappa;
import com.mba.tappa.Tappa.ErrorCallback;

public class TappaModule extends ReactContextBaseJavaModule {
    private static final String TAG = "TappaModule";
    private Tappa tappa;

    public TappaModule(ReactApplicationContext reactContext) {
        super(reactContext);
        tappa = new Tappa();
    }

    @Override
    public String getName() {
        return "Tappa";
    }

    @ReactMethod
    public void initializeTerminal() {
        try {
            tappa.initialize(getCurrentActivity(), "merchantId", "terminalId");
        } catch (Exception e) {
            Log.e(TAG, "Initialization failed", e);
        }
    }

    @ReactMethod
    public void setErrorHandler() {
        try {
            tappa.setErrorCallback(new ErrorCallback() {
                @Override
                public void onError(String error) {
                    Log.e(TAG, "Tappa Error: " + error);
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Error handler setup failed", e);
        }
    }

    @ReactMethod
    public void startTransaction(String amount, Callback onSuccess, Callback onError) {
        try {
            tappa.performTransaction(amount, new Tappa.TransactionCallback() {
                @Override
                public void onSuccess(String response) {
                    onSuccess.invoke(response);
                }

                @Override
                public void onFailure(String error) {
                    onError.invoke(error);
                }
            });
        } catch (Exception e) {
            onError.invoke("Transaction failed: " + e.getMessage());
        }
    }
}
