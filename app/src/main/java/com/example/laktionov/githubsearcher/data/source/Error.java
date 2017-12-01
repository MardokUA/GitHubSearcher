package com.example.laktionov.githubsearcher.data.source;

import com.example.laktionov.githubsearcher.R;

public class Error {

    public static final int ERROR_EMPTY_QUERY = 44;
    public static final int ERROR_FOUND_NOTHING = 45;
    public static final int ERROR_SERVER_RESPONSE = 46;
    public static final int ERROR_INTERNAL = 47;

    private final int mError;
    private String mMessage;

    public Error(int mError) {
        this.mError = mError;
    }

    public int getError() {
        int messageId;
        switch (mError) {
            case ERROR_EMPTY_QUERY:
                messageId = R.string.error_empty_query;
                break;
            case ERROR_FOUND_NOTHING:
                messageId = R.string.error_find_nothing;
                break;
            case ERROR_SERVER_RESPONSE:
                messageId = R.string.error_internet_connection;
                break;
            default:
                messageId = R.string.error_external;
                break;
        }
        return messageId;
    }

    public void setMessage(String message) {
        mMessage = message;
    }
}
