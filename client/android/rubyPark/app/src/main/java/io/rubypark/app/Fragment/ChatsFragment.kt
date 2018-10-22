package io.rubypark.app.Fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import io.rubypark.app.R
import org.w3c.dom.Text

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ChatsFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ChatsFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ChatsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    // private var param1: String? = null
    // private var param2: String? = null
    // private var listener: OnFragmentInteractionListener? = null

    private lateinit var mContext: Context
    private lateinit var mView: View

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mContext = inflater.context

        mView = LinearLayout(mContext)

        val mText: TextView = TextView(mContext)
        mText.text = "hello !!!!"

        // val v: View = inflater.inflate(R.layout.fragment_assets_detail, container, false)
        return mView

    }


}
