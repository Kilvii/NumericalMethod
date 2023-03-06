import java.lang.Math.abs

fun main() {
    val Points = arrayOf(1.0, 1.1, 1.2, 1.3, 1.4, 1.5, 1.6, 1.7, 1.8, 1.9, 2.0)
    val Values = arrayOf(2.7182, 3.0041, 3.3201, 3.6692, 4.0552, 4.4816, 4.9530, 5.4739, 6.0496, 6.6858, 7.3890)

    AitkenScheme(Points,Values,1.43,11,0.1)

}

fun AitkenScheme(x : Array<Double>,y : Array<Double>, point: Double, N:Int, epsilon:Double): Double {
    val Elements: Array<Array<Double>> = Array(N) { Array(N) { 0.0 } }
    val Epsilons: Array<Array<Double>> = Array(N) { Array(N) { 1.0 } }
    val result: Double
    var epsMin = 1.0
    var i0 = 0
    var j0 = 0

    for(i in 0 until N){
        Elements[i][i]=y[i]
        for (j in i-1 downTo 1){
            Elements[j][i] = ((point - x[j])*Elements[j+1][i]-(point-x[i])*Elements[j][i-1])/(x[i]-x[j])

            Epsilons[j][i] = abs(Elements[j][i]-Elements[j-1][i-1])

            if(Epsilons[j][i] <= epsMin){
                epsMin = Epsilons[j][i]
                i0 = i
                j0 = j
            }

            /*if(abs(Elements[j][i]-Elements[j-1][i-1])< epsilon){
                result = Elements[j][i]
                i0 = i
                j0 = j
                println("The result of the program execution is $result. Row $j0 Colimn $i0")
                return result
            }*/
        }
    }
    result = Elements[j0][i0]
    println("The result of the program execution is $result")
    for (i in 0 until N){
        for (j in 0 until N){
            println(Elements[i][j])
        }
        println()
    }
    return result

}

/*
   for (i in 0 until N) {
       for (j in 0 until M) {
           if (i == 0) {
               Elements[i][j] = y.get(j)
           } else {
               Elements[i][j] = (Elements[i - 1][j + 1] * (point - x.get(j)) - Elements[i - 1][j] * (point - x.get(j+1))) / (x.get(j+1) - x.get(j))
               buffer.add(Elements[i][j])
               step+=1
               if(buffer.size > 1){
                   Epsilons[i][j] = abs(buffer[step] - buffer[step-1])
                   if(Epsilons[i][j] <= epsilon){
                       result = Elements[i][j]
                       return result
                   }
                   if(Epsilons[i][j] <= epsMin){
                       epsMin = Epsilons[i][j]
                       i0 = i
                       j0 = j
                   }
               }
           }
       }
       M = M - 1
   }
   result = Elements[i0][j0]
   */
/*
val X = 1.43
val eps = 0.01
var result = 1.0
val n = Points.size
var m = n
var step = 0
var epsMin = 1.0
val eps = 0.001
var i0 = 0
var j0 = 0

var part1 = 0.0
var part2 = 0.0
var part3 = 0.0

val Elements: Array<Array<Double>> = Array(n, { Array(n, {0.0}) })
val Epsilons: Array<Array<Double>> = Array(n, { Array(n, {1.0}) })
var buffer = mutableListOf<Double>()


for (i in 0 until n){
    for (j in 0 until m){
        if(i == 0) {
            Elements[i][j] = Values[j]
        }
        else{
            part1 = Elements[i-1][j+1]*(X-Points[j])
            part2 = Elements[i-1][j]*(X-Points[j+1])
            part3 = (Points[j+1] - Points[j])
            Elements[i][j] = (part1 - part2)/part3
        }
        buffer.add(Elements[i][j])
        if(buffer.size > 1){
            Epsilons[i][j] = abs(buffer[step] - buffer[step-1])
            if(Epsilons[i][j] <= epsMin){
                epsMin = Epsilons[i][j]
                i0 = i
                j0 = j
            }
            if(Epsilons[i][j] <= eps){
                result = Elements[i][j]
                break
            }
        }
        step+=1
    }
    m-=1
}

result = Elements[i0][j0]

println("The result of the program execution is $result")
 */