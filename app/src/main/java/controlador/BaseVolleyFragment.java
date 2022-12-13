package controlador;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;

@SuppressWarnings({"unchecked", "rawtypes"})
public class BaseVolleyFragment extends Fragment {
    private VolleySingleton volley;
    protected RequestQueue fRequestQueue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        volley = VolleySingleton.getInstance(requireActivity().getApplicationContext());
        fRequestQueue = volley.getRequestQueue();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (fRequestQueue != null) fRequestQueue.cancelAll(this);
    }

    public void addToQueue(Request request) {
        if (request != null) {
            request.setTag(this);
            if (fRequestQueue == null) fRequestQueue = volley.getRequestQueue();
            request.setRetryPolicy(new DefaultRetryPolicy(60000, 3,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            fRequestQueue.add(request);
        }
    }
}