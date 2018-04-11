package MODEL

/**
 * Created by Liang Zihong on 2018/3/8.
 */

class Message(var content: String?, var state: Int) {
    companion object {
        val Is_Sent = 0
        val Is_Receive = 1
    }
}
