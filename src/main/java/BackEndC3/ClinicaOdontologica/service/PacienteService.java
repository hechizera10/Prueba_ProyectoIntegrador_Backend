package BackEndC3.ClinicaOdontologica.service;

import BackEndC3.ClinicaOdontologica.entity.Paciente;
import BackEndC3.ClinicaOdontologica.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    //metodos manuales
    public Paciente guardarPaciente(Paciente paciente){
        return pacienteRepository.save(paciente);
    }
    public Optional<Paciente> buscarPorID(Long id){
        return pacienteRepository.findById(id);
    }
    public List<Paciente> buscarTodosLosPacientes(){return pacienteRepository.findAll();}
    public Optional<Paciente> buscarPorEmail(String email){
        return pacienteRepository.findByEmail(email);
    }

    public void actualizarPaciente(Paciente paciente){
        pacienteRepository.save(paciente);
    }

    public void eliminarPaciente(Long id){
        pacienteRepository.deleteById(id);
    }

}
