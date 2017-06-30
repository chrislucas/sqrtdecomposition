import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by r028367 on 30/06/2017.
 *
 * Algoritmo utilizado para a seguinte categoria
 *
 * Dado um array S [] de tamanho N, e Q consultas realizadas
 * num subarray 0 <= Par(L, R) < N que aplicam uma funcao F
 * no subconjutno S[L ... R]
 *
 * Exemplo
 *
 * Dado um array S de tamanho N, para cada i consultas (L, R)
 * aplicar a soma dos elementos no subconjunto S[L, R]
 *
 */

public class Mo {

    public static int [] array;
    public static IntPair [] queries;

    static class IntPair {
        int l, r;
        public IntPair(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }

    public static void solver() {
        int blockSize = (int)Math.sqrt(queries.length);
        Comparator<IntPair> comparator = new Comparator<IntPair>() {
            /**
             * Ordenando os paras usando a regra de Mo's order
             * */
            @Override
            public int compare(IntPair p1, IntPair p2) {
                int l1 = p1.l / blockSize, l2 = p2.l / blockSize;
                if(l1 == l2) {
                    int r1 = p1.r, r2 = p2.r;
                    return r1 - r2;
                }
                else
                    return l1 - l2;
            }
        };
        Arrays.sort(queries, comparator);
        // valores para uma funcao fn que sera aplicado no subarray L .. R
        int sum = 0, currL = 0, currR = 0;
        // loop atraves do vetor de queries
        for(IntPair pair : queries) {
            // index para o subarray array[L, R]
            int L = pair.l, R = pair.r;
            fn(L, R, currL, currR, sum);
         }
    }

    public static void fn(int L, int R, int currL, int currR, int sum) {
        /**
         * removendo os valores do subconjunto anterior
         * Exemplo
         * Anteriormente L, R eram [0, 5], agora
         * sao [2, 6]. Entao o codigo abaixo subtrai da
         * soma os valores dos indices (0, 1)
         * */
        for(;currL<L; currL++) {
            sum -= array[currL];
        }
        /**
         *  adicionado os valores do lado esquerdo do subconjutno
         *  L, R
         * */
        for(;currL>L;currL--) {
            sum += array[currL];
        }
        // somando os valores  de currR a R
        for(;currR<=R;currR++) {
            sum += array[currR];
        }
        /**
         * removendo os valores do subconjutno anterior
         * Exemplo
         * */
        for(;currR>R+1;currR--) {
            sum -= array[currR];
        }
        System.out.printf("(%d %d) %d\n", L, R, sum);
    }

    public static void add(int idx, int l, int r) {
        queries[idx] = new IntPair(l, r);
    }

    public static void reader() {
        queries = new IntPair[10];
        add(0, 0, 4);
        add(1, 1, 4);
        add(2, 2, 4);
        array = new int [] {1, 1, 2, 1, 3, 4, 5, 2, 8};
        solver();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int queries = Integer.parseInt(bufferedReader.readLine());
        } catch (IOException ioex) {}
    }




    public static void main(String[] args) {
        reader();
    }


}
