/**
 * Created by 1 on 08.11.2017.
 */
public class Matrix
{
    public static void main(String[] argv)
    {
        int m = 6;
        int[][] A = new int[m][m];
        // fill field
        for (int i=0; i<m; i++)
        {
            for (int j=0; j<m; j++)
            {
                A[i][j] = m*i + j;
            }
        }
        // printField
        for (int i=0; i<m; i++)
        {
            for (int j=0; j<m; j++)
            {
                System.out.printf("%3d ", A[i][j]);
            }
            System.out.print("\n");
        }
        System.out.print("\nrotated\n\n");
        // rotate
        for (int k=0; k<m/2; k++) // border -> center
        {
            for (int j=k; j<m-1-k; j++) // left -> right
            {
                // меняем местами 4 угла
                int tmp         = A[k][j];
                A[k][j]         = A[j][m-1-k];
                A[j][m-1-k]     = A[m-1-k][m-1-j];
                A[m-1-k][m-1-j] = A[m-1-j][k];
                A[m-1-j][k]     = tmp;
            }
        }
        // printField
        for (int i=0; i<m; i++)
        {
            for (int j=0; j<m; j++)
            {
                System.out.printf("%3d ", A[i][j]);
            }
            System.out.print("\n");
        }
    }
}
