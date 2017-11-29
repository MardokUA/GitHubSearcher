package com.example.laktionov.githubsearcher.data.source;

import com.example.laktionov.githubsearcher.R;

public class Error {

    public static final int ERROR_EMPTY_QUERY = 44;
    public static final int ERROR_FOUND_NOTHING = 45;
    public static final int ERROR_INTERNET_CONNECTION = 46;

    private final int mError;

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
            case ERROR_INTERNET_CONNECTION:
                messageId = R.string.error_internet_connection;
                break;
            default:
                messageId = R.string.error_external;
                break;
        }
        return messageId;
    }
}
