import java.lang.Math.abs
import java.lang.Math.pow
import kotlin.math.pow
fun main(){
    val x = arrayOf(0.0,0.01,0.02,0.03,0.04,0.05,0.06,0.07,0.08,0.09,0.1)
    val y =arrayOf(1.0,0.9791,0.9594,0.9419,0.9196,0.9035,0.8826,0.8629,0.8464,0.8271,0.811)
    val testx = arrayOf(1.0,2.0,3.0,4.0,5.0,6.0)
    val testy = arrayOf(1.0,1.5,3.0,4.5,7.0,8.5)

    val matrix: Array<Array<Double>> = Array(3) { Array(3) { 0.0 } }
    var bufferA = 0.0
    var bufferB = 0.0
    var bufferC = 0.0
    var con = 4.0
    var rightSide = Array(3,{0.0})
    for (i in 0 until 3){
        for (j in 0 until x.size){
            bufferA += x[j].pow(con)
            bufferB += x[j].pow(con - 1.0)
            bufferC += x[j].pow(con - 2.0)
            rightSide[i] += x[j].pow(con - 2.0) * y[j]
        }
        con -= 1.0
        matrix[i][0] = bufferA
        matrix[i][1] = bufferB
        matrix[i][2] = bufferC
        bufferA =0.0
        bufferB =0.0
        bufferC =0.0
    }

    //val koefs = Zeidel(matrix,rightSide,rightSide.size)
    val koefs = Gauss(matrix,rightSide,rightSide.size)
    println("The desired function: y = ${koefs[0]}*X^2 + ${koefs[1]}*X + ${koefs[2]} ")

}

fun Zeidel(matrix: Array<Array<Double>>,rightSide:Array<Double>,N:Int):Array<Double>{
    val eps = 1e-10
    val result = Array(N,{1.0})
    var buffer = Array(N,{0.0})
    var Convergence = true
    while (Convergence){
        Convergence = false
        for (i in 0 until N){
            buffer[i] = rightSide[i]
            for (j in 0 until N){
                if(j != i){
                    buffer[i] -= matrix[i][j] * result[i]
                }
            }
            buffer[i] /= matrix[i][i]
            if(abs(buffer[i] - result[i]) > eps){
                Convergence = true
            }
            result[i] = buffer[i]
        }
    }
    return result
}
fun Gauss(matrix: Array<Array<Double>>,rightSide:Array<Double>,N:Int):Array<Double>{
    var i = 0
    val result = Array(N,{1.0})
    while (i < N){
        var maxCol = i
        for(j in i+1 until N){
            if(abs(matrix[j][i]) > abs(matrix[maxCol][i])){
                maxCol = j
            }
        }
        if(maxCol != i){
            matrix[i] = matrix[maxCol].also { matrix[maxCol] = matrix[i] }
            rightSide[i] = rightSide[maxCol].also { rightSide[maxCol] = rightSide[i] }
        }
        for (j in i+1 until N){
            var ratio = matrix[j][i] / matrix[i][i]
            for (k in i until N){
                matrix[j][k] -= (ratio * matrix[i][k])
            }
            rightSide[j] -= (ratio*rightSide[i])
        }
        i++
    }
    for (i in N-1 downTo 0){
        for (j in i+1 until N){
            rightSide[i] -= (matrix[i][j] * result[j])
        }
        result[i] = rightSide[i] / matrix[i][i]
    }
    return result
}
