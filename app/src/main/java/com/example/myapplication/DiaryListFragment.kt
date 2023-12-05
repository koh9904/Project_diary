package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentDiaryListBinding
import com.example.myapplication.viewmodel.DiaryListViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
/*import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentDiaryListBinding
import com.example.myapplication.viewmodel.CalendarContentViewModel

 */
class DiaryListFragment : Fragment() {
    private var _binding: FragmentDiaryListBinding? = null
    private val binding get() = _binding ?: throw IllegalArgumentException("Binding Error")
    private val viewModel by viewModels<DiaryListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDiaryListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllDiaries()
        initUI()
        /*observeDiaryList()

         */
    }



    /*private fun updateRecyclerView(date: String, content: String){
        //RecyclerView 업데이트 로직을 여기에 구현
    }

     */

    private fun initUI() {
        binding.diarlylist.adapter = DiaryAdapter { diary ->
            DiaryListFragmentDirections.actionDiaryListFragmentToDiaryFragment(diary).let { action ->
                findNavController().navigate(action)
            }
        }

        lifecycleScope.launch {
            viewModel.diaries.collect {
                (binding.diarlylist.adapter as DiaryAdapter).changeDiaryData(it)
                (binding.diarlylist.adapter as DiaryAdapter).notifyDataSetChanged()
            }
        }

        /*binding.diarlylist.adapter = DiaryAdapter{ diary ->
            DiaryListFragmentDirections.actionDiaryListFragmentToDiaryFragment(diary).let{action ->
                findNavController().navigate(action)
            }
        }

         */
    }

    /*
    private fun observeDiaryList(){
        viewModel.diaryList.observe(viewLifecycleOwner){ diaryList ->
            (binding.diarlylist.adapter as DiaryAdapter).changeDiaryData(diaryList)
        }
    }

     */

    companion object {
        const val SELECTED_DIARY_DATA = "SELECTED_DIARY_DATA"
        const val SELECTED_DIARY = "SELECTED_DIARY"
        val dateFormat = SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREAN)
    }
}