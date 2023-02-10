package fingerfire.com.valorant.view.ui.agents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import fingerfire.com.valorant.R
import fingerfire.com.valorant.databinding.FragmentHomeBinding
import fingerfire.com.valorant.view.adapter.AgentAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

/** Classe do fragment responsável pela apresentação da tela
 * Já com injeção de dependencia
 * Usando Observer para "escutar" as alterações na class de viewmodel
 * Fazendo chamada da view model para trazer lista de agentes
 * e tbm fazendo uso da palavra reservada viewmodel junto com o BY*/
class AgentFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: AgentViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerAgents()

        viewModel.getAgents()
    }

    private fun observerAgents() {
        viewModel.agentsLiveData.observe(viewLifecycleOwner) {
            binding.rvAgents.layoutManager = LinearLayoutManager(activity)
            binding.rvAgents.setHasFixedSize(true)
            binding.rvAgents.adapter = AgentAdapter(it.agents)

        }
    }
}