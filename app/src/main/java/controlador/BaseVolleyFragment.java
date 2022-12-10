package controlador;

import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;

public class BaseVolleyFragment extends Fragment {
    private VolleySingleton volley;
    protected RequestQueue fRequestQueue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        volley = VolleySingleton.getInstance(getActivity().getApplicationContext());
        fRequestQueue = volley.getRequestQueue();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (fRequestQueue != null) {
            fRequestQueue.cancelAll(this);
        }
    }

    public void addToQueue(Request request) {
        if (request != null) {
            request.setTag(this);
            if (fRequestQueue == null)
                fRequestQueue = volley.getRequestQueue();
            request.setRetryPolicy(new DefaultRetryPolicy(
                    60000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
            ));
            onPreStartConnection();
            fRequestQueue.add(request);
        }
    }public void onPreStartConnection() {
    }

    public void onConnectionFinished() {
    }

    public void onConnectionFailed(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

}
