import java.util.*;

public class WarungIkanMagetan {

    static class Pelanggan implements Comparable<Pelanggan> {
        int id, budget, patience, arrivalTime, currentTime;

        public Pelanggan(int id, int budget, int patience, int arrivalTime) {
            this.id = id;
            this.budget = budget;
            this.patience = patience;
            this.arrivalTime = arrivalTime;
            this.currentTime = arrivalTime;
        }

        @Override
        public int compareTo(Pelanggan other) {
            if (this.budget != other.budget) return Integer.compare(other.budget, this.budget);
            if (this.patience != other.patience) return Integer.compare(this.patience, other.patience);
            return Integer.compare(this.id, other.id);
        }
    }

    static int waktu = 0;
    static int nextId = 0;
    static PriorityQueue<Pelanggan> antrian = new PriorityQueue<>();
    static Map<Integer, Pelanggan> idMap = new HashMap<>();
    static Deque<Integer> kupon = new ArrayDeque<>();
    static List<Integer> hargaIkan;
    static int[] hargaSuvenir, nilaiSuvenir;
    static Map<Integer, Integer> hasilMemo = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // jumlah ikan
        int m = sc.nextInt(); // jumlah suvenir
        int q = sc.nextInt(); // jumlah query

        hargaIkan = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            hargaIkan.add(sc.nextInt());
        }

        hargaSuvenir = new int[m];
        for (int i = 0; i < m; i++) hargaSuvenir[i] = sc.nextInt();
        
        nilaiSuvenir = new int[m];
        for (int i = 0; i < m; i++) nilaiSuvenir[i] = sc.nextInt();

        sc.nextLine();
        for (int i = 0; i < q; i++) {
            waktu++;
            String[] parts = sc.nextLine().split(" ");
            switch (parts[0]) {
                case "A":
                    int budget = Integer.parseInt(parts[1]);
                    int patience = Integer.parseInt(parts[2]);
                    Pelanggan p = new Pelanggan(nextId, budget, patience, waktu);
                    antrian.add(p);
                    idMap.put(nextId, p);
                    System.out.println(nextId);
                    nextId++;
                    break;
                case "S":
                    int x = Integer.parseInt(parts[1]);
                    int selisih = cariSelisih(x);
                    System.out.println(selisih);
                    break;
                case "L":
                    int id = Integer.parseInt(parts[1]);
                    if (!idMap.containsKey(id)) {
                        System.out.println(-1);
                    } else {
                        Pelanggan keluar = idMap.remove(id);
                        antrian.remove(keluar);
                        System.out.println(keluar.budget);
                    }
                    break;
                case "D":
                    int diskon = Integer.parseInt(parts[1]);
                    kupon.push(diskon);
                    System.out.println(kupon.size());
                    break;
                case "B":
                    while (!antrian.isEmpty()) {
                        Pelanggan top = antrian.peek();
                        if (waktu >= top.arrivalTime + top.patience) {
                            antrian.poll();
                            idMap.remove(top.id);
                            continue;
                        }
                        int harga = cariIkanTermahal(top.budget);
                        if (harga == -1) {
                            System.out.println(top.id);
                            antrian.poll();
                            idMap.remove(top.id);
                        } else {
                            int bayar = harga;
                            if (top.budget == harga && !kupon.isEmpty()) {
                                int d = kupon.pop();
                                bayar = Math.max(1, harga - d);
                            }
                            int kembalian = top.budget - bayar;
                            top.budget -= bayar;
                            top.arrivalTime = waktu;
                            if (top.budget == harga) kupon.pop();
                            else if (kembalian > 0) kupon.push(kembalian);
                            System.out.println(kembalian);
                            break;
                        }
                    }
                    if (antrian.isEmpty()) System.out.println(-1);
                    break;
                case "O":
                    int tipe = Integer.parseInt(parts[1]);
                    int uang = Integer.parseInt(parts[2]);
                    if (tipe == 1) {
                        System.out.println(maxKebahagiaan(uang));
                    } else {
                        List<Integer> hasil = new ArrayList<>();
                        System.out.println(maxKebahagiaanLeksikografis(uang, hasil));
                        for (int idx : hasil) System.out.print(idx + " ");
                        System.out.println();
                    }
                    break;
            }
        }
    }

    static int cariSelisih(int target) {
        int left = 0, right = hargaIkan.size() - 1;
        int minDiff = Integer.MAX_VALUE;
        while (left <= right) {
            int mid = (left + right) / 2;
            minDiff = Math.min(minDiff, Math.abs(hargaIkan.get(mid) - target));
            if (hargaIkan.get(mid) < target) left = mid + 1;
            else right = mid - 1;
        }
        return minDiff;
    }

    static int cariIkanTermahal(int budget) {
        int res = -1;
        for (int i = hargaIkan.size() - 1; i >= 0; i--) {
            if (hargaIkan.get(i) <= budget) {
                res = hargaIkan.get(i);
                break;
            }
        }
        return res;
    }

    static int maxKebahagiaan(int uang) {
        int m = hargaSuvenir.length;
        int[][] dp = new int[m + 1][3];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 3; j++) {
                if (dp[i][j] > dp[i + 1][0]) dp[i + 1][0] = dp[i][j];
            }
            for (int j = 0; j < 2; j++) {
                if (dp[i][j] + hargaSuvenir[i] <= uang) {
                    dp[i + 1][j + 1] = Math.max(dp[i + 1][j + 1], dp[i][j] + nilaiSuvenir[i]);
                }
            }
        }

        return Math.max(dp[m][0], Math.max(dp[m][1], dp[m][2]));
    }

    static int maxKebahagiaanLeksikografis(int uang, List<Integer> path) {
        int m = hargaSuvenir.length;
        int[][][] dp = new int[m + 1][uang + 1][3];
        int[][][] from = new int[m + 1][uang + 1][3];
        for (int[][] mat : from) for (int[] row : mat) Arrays.fill(row, -1);

        for (int i = 0; i < m; i++) {
            for (int u = 0; u <= uang; u++) {
                for (int k = 0; k < 3; k++) {
                    if (dp[i][u][k] >= 0) {
                        // skip
                        if (dp[i][u][k] > dp[i + 1][u][0]) {
                            dp[i + 1][u][0] = dp[i][u][k];
                            from[i + 1][u][0] = k;
                        }
                        // ambil jika boleh
                        if (k < 2 && u + hargaSuvenir[i] <= uang) {
                            int val = dp[i][u][k] + nilaiSuvenir[i];
                            if (val > dp[i + 1][u + hargaSuvenir[i]][k + 1]) {
                                dp[i + 1][u + hargaSuvenir[i]][k + 1] = val;
                                from[i + 1][u + hargaSuvenir[i]][k + 1] = k;
                            }
                        }
                    }
                }
            }
        }

        int maxVal = -1, maxU = 0, maxK = 0;
        for (int u = 0; u <= uang; u++) {
            for (int k = 0; k < 3; k++) {
                if (dp[m][u][k] > maxVal) {
                    maxVal = dp[m][u][k];
                    maxU = u;
                    maxK = k;
                }
            }
        }

        for (int i = m; i > 0; i--) {
            int prevK = from[i][maxU][maxK];
            if (prevK == -1) break;
            if (maxK > 0 && dp[i][maxU][maxK] != dp[i - 1][maxU - hargaSuvenir[i - 1]][prevK] + nilaiSuvenir[i - 1]) {
                // tidak diambil
            } else {
                path.add(0, i - 1);
                maxU -= hargaSuvenir[i - 1];
            }
            maxK = prevK;
        }
        return maxVal;
    }
}
