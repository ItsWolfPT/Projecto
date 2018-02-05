package projecto;
import javax.swing.JOptionPane;
import java.util.Formatter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Projecto
{

    public static void main(String[] args) throws FileNotFoundException
    {
        final int TAMANHO =50;
        String[] nomes = new String[TAMANHO];
        int[] notas = new int[TAMANHO];
        int nElems=0, op;
        do
        {
          op= menu();
          switch (op)
          {
             case 0:
                  if (nElems < TAMANHO)
                  {
                    inserir(nomes, notas, nElems);
                    nElems++;
                  } 
                  else
                  {
                      JOptionPane.showMessageDialog(null, "Atingida a capacidade maxima do vetor");
                  }
                  break;
              case 1:
                  if (nElems > 0)
                  {
                    listar(nomes, notas, nElems);
                  } 
                  else
                  {
                      JOptionPane.showMessageDialog(null, "Nao Existem alunos");
                  }
                  break; 
              case 2:
                  if (nElems > 0)
                  {
                    atualizar(nomes, notas, nElems);
                  } 
                  else
                  {
                      JOptionPane.showMessageDialog(null, "Nao Existem alunos");
                  }
                  break;
              case 3:
                  if (nElems>0)
                  {
                      if(eliminar(nomes, notas, nElems)== true)
                      {
                      JOptionPane.showMessageDialog(null, "Aluno Eliminado");
                      nElems--;
                      }
                      else
                      {
                      JOptionPane.showMessageDialog(null,"Nao há qualquer aluno com esse nome");
                      }
                  }
                  else
                  {
                      JOptionPane.showMessageDialog(null, "Nao existem alunos");
                  }   
                  break;
              case 4:
                  if (nElems > 0)
                  {
                      ordenar(nomes, notas, nElems);
                      listar(nomes, notas, nElems);
                  }
                  else
                  {
                      JOptionPane.showMessageDialog(null, "Nao exitem alunos");
                  }
                  break;
              case 5:
                  if(nElems > 0)
                  {
                      gravar(nomes, notas, nElems);
                  }
                  else
                  {
                      JOptionPane.showMessageDialog(null, "Nao existem alunos");
                  }
              case 6:
                  
                  
              case 9:
                  JOptionPane.showMessageDialog(null, "Fim...");
                  break;
          }
        } 
        while (op !=9);
    }

    private static int menu() 
    {
      int op;
      op = Integer.parseInt(JOptionPane.showInputDialog(
        "0- Adicionar aluno\n"
      + "1- Listar alunos\n" 
      + "2- Atualizar nota de um aluno\n" 
      + "3- Eliminar aluno\n"
      + "4- Mostrar Informaçao ordenada por nome do aluno\n" 
      + "5- Carregar dados para a memoria\n"
      + "6-Gravar Dados em Ficheiro\n" 
      + "9 - Sair\n"));
      return op;
    }

    private static void inserir(String[] nomes, int[] notas, int nElems) 
    {
        nomes[nElems] = JOptionPane.showInputDialog("Nome?");
        notas[nElems] = Integer.parseInt(JOptionPane.showInputDialog("notas"));
    }

    private static void listar(String[] nomes, int[] notas, int nElems) 
    {
        int x;
        String msg= "Listagem de alunos";
        for (x=0; x<nElems;x++)
        { 
        msg+= "\n" + nomes[x] + "-" + notas[x];
        }
        JOptionPane.showMessageDialog(null, msg);
    }

    private static void atualizar(String[] nomes, int[] notas, int nElems) 
    {
       String nome;
       int pos;
       nome = JOptionPane.showInputDialog("qual o aluno cujo nota pretende alterar");
       pos = pesquisar(nomes,nElems,nome);
       if (pos != -1)
       {
            notas[pos] = Integer.parseInt(JOptionPane.showInputDialog("Qual o Novo nota?"));
       }
       else
       {
            JOptionPane.showMessageDialog(null,"Nao ha qualquer aluno com o nome introduzido");
           
       }
    }

    private static int pesquisar(String[] nomes, int nElems, String nome) 
    {
        int pos=0;
        while(pos<nElems && nome.equalsIgnoreCase(nomes[pos])== false)
        {
           pos++; 
        }
        if (pos< nElems)
        {
            return pos;
        }
        else
        {
            return -1;
        }
    }

    private static boolean eliminar(String[] nomes, int[] notas, int nElems) 
    {
        String nome;
        int pos,x;
        nome =JOptionPane.showInputDialog("Qual é o funcinario que pretende Eliminar?");
        pos = pesquisar(nomes,nElems, nome);
        if(pos != -1)
        {
            for (x=pos;x<nElems;x++)
            {
                nomes[x]=nomes[x+1];
                notas[x] = notas [x+1];
            }
            return true;
        }
        else
        {
            return false;
        }
    }

    private static void ordenar(String[] nomes, int[] notas, int nElems) 
    {
        int x , y , aux1;
        String aux2;
        for(x=0; x< nElems; x++)
        {
            for(y=0; y<nElems; y++)
            {
                if(nomes[y].compareToIgnoreCase(nomes[x])<0)
                {
                    aux2=nomes[x];
                    nomes[x]=nomes[y];
                    nomes[y]=aux2;
                    aux1= notas[x];
                    notas[x]=notas[y];
                    notas[y]=aux1;
                            
                }
            }
        }
    }

    private static void gravar(String[] nomes, int[] notas, int nElems) throws FileNotFoundException 
    {
        Formatter files3 = new Formatter(new File("alunos.txt"));
        int x;
        for (x=0; x < nElems; x++)
        {
            if(x==0)
            {
                files3.format(nomes[x]+ ":" + notas[x]);
            }
            else
            {
                files3.format("\n" + nomes[x]+ ":" + notas[x]);
            }
            files3.close();
        }
        
    }
    private static int ler(String[] nomes, int[] notas, int nElems) throws FileNotFoundException {
        Scanner fichFunc = new Scanner(new File("alunos.txt"));
        String linha;
        int pos, cont = 0;
        
        while(fichFunc.hasNextLine()==true && nElems < nomes.length) {
            linha = fichFunc.nextLine();
            String[] vetLinha = linha.split(":");
            pos = pesquisar(nomes, nElems, vetLinha[0]);
            if (pos == -1) {
                nomes[nElems] = vetLinha[0];
                notas[nElems] = Integer.parseInt(vetLinha[1]);
                nElems++;
                cont++;
            }
        }
        fichFunc.close();
        JOptionPane.showMessageDialog(null, "Numero de alunos carregados: " + cont);
        return nElems;
    }
}