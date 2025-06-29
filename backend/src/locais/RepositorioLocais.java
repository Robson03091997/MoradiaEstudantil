package locais;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RepositorioLocais {
    private static RepositorioLocais instancia;
    private Map<String, Local> locais;
    
    private RepositorioLocais() {
        this.locais = new HashMap<>();
    }
    
    // Padrão Singleton
    public static RepositorioLocais getInstancia() {
        if (instancia == null) {
            instancia = new RepositorioLocais();
        }
        return instancia;
    }
    
    // Adicionar local
    public void adicionarLocal(Local local) {
        if (local != null) {
            locais.put(local.getId(), local);
        }
    }
    
    // Remover local
    public boolean removerLocal(String id) {
        return locais.remove(id) != null;
    }
    
    // Buscar local por ID
    public Local buscarPorId(String id) {
        return locais.get(id);
    }
    
    // Buscar local por endereço
    public List<Local> buscarPorEndereco(String endereco) {
        return locais.values().stream()
                .filter(local -> local.getEndereco().toLowerCase().contains(endereco.toLowerCase()))
                .collect(Collectors.toList());
    }
    
    // Buscar locais por tipo
    public List<Local> buscarPorTipo(String tipo) {
        return locais.values().stream()
                .filter(local -> local.getTipoLocal().equalsIgnoreCase(tipo))
                .collect(Collectors.toList());
    }
    
    // Listar todos os locais
    public List<Local> listarTodos() {
        return new ArrayList<>(locais.values());
    }
    
    // Obter quantidade de locais
    public int getQuantidadeLocais() {
        return locais.size();
    }
    
    // Verificar se local existe
    public boolean localExiste(String id) {
        return locais.containsKey(id);
    }
    
    // Obter estatísticas por tipo
    public Map<String, Long> getEstatisticasPorTipo() {
        return locais.values().stream()
                .collect(Collectors.groupingBy(
                    Local::getTipoLocal,
                    Collectors.counting()
                ));
    }
    
    // Limpar repositório (útil para testes)
    public void limpar() {
        locais.clear();
    }
    
    // Obter locais disponíveis (sem moradores)
    public List<Local> getLocaisDisponiveis() {
        return locais.values().stream()
                .filter(local -> local.getMoradores().isEmpty())
                .collect(Collectors.toList());
    }
    
    // Obter locais ocupados
    public List<Local> getLocaisOcupados() {
        return locais.values().stream()
                .filter(local -> !local.getMoradores().isEmpty())
                .collect(Collectors.toList());
    }
} 