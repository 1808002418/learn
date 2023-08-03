package org.example.ch3.backtrack.test;

import org.example.ch3.backtrack.ListLexer;
import org.example.ch3.backtrack.BacktrackParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BacktrackParserTest {
    public static void main(String[] args) {
        String input="[a,v,c]=[q,w,e]";
        ListLexer lexer = new ListLexer(input);
        BacktrackParser parser = new BacktrackParser(lexer);
        parser.stat();


        String message="<14>2023-07-25 12:50:59|!alarm|!44.32.240.70|!{\"attack_classify_id\": 20000, \"attack_state\": 1, \"dev_id\": \"D21A362F\", \"module_type_name\": \"服务探测\", \"status_code\": [], \"event_desc\": \"TCP同一端口多主机扫描\", \"updated_at\": 1690260484, \"brief\": \"TCP同一端口多主机扫描\", \"suffer_branch_id\": 0, \"suffer_country\": \"未知\", \"alert_id\": 217820081, \"reliability\": 2, \"relation\": \"{\\\"cond\\\":{\\\"hole_id\\\":\\\"${hole_ids}\\\",\\\"src_asset_id\\\":\\\"${attack_asset_id}\\\"},\\\"from\\\":\\\"ngfw.security\\\",\\\"type\\\":\\\"es\\\"}\", \"is_white\": 0, \"last_time\": 1690259879, \"x_forwarded_for\": [], \"attack_type\": 1, \"sub_attack_type_name\": \"\", \"suggest\": \"1、确认攻击者地址，若为内网地址则确认相关责任人，排除人为因素，对主机进行杀毒处理\\\\n\\\\n2、外网地址则配置限制登陆次数策略，检查攻击源，封禁ip\", \"suffer_ip\": \"147.14.39.169\", \"tags\": \"TCP同一端口多主机扫描\", \"damage\": \"主机扫描其他多个主机的统一端口，可以判断有哪些主机上运行了特定的服务，进而针对性的进行攻击\", \"multi_deal_status\": 0, \"event_evidence\": \"开始时间: 2023-07-25 12:27:33 结束时间: 2023-07-25 12:37:59 持续时间（秒）: 626 扫描主机数: 934 协议: tcp 模型描述: TCP同一端口多主机扫描 描述信息: [u'\\\\u5185\\\\u7f51\\\\u4e3b\\\\u673a 172.131.23.53 \\\\u5bf9\\\\u591a\\\\u4e2a\\\\u5916\\\\u7f51\\\\u4e3b\\\\u673a [173.182.66.150, 103.179.113.244, 176.72.32.113, 61.221.229.6, 221.94.58.167, 117.160.41.20, 190.185.128.169, 121.176.125.147, 221.19.220.104, 151.80.75.112, 210.165.49.125, 39.223.49.6, 84.99.97.65, 173.15.10.115, 40.232.9.42, 209.11.195.68, 113.157.190.53, 162.24.178.97, 12.69.130.208, 121.162.103.51, 124.75.69.129, 170.136.126.122, 162.48.44.179, 180.112.184.215, 223.136.83.187, 19.127.142.239, 165.115.71.180, 138.81.205.113, 159.169.249.21, 133.220.187.249, 100.240.61.128, 142.45.187.187, 223.91.223.178, 202.145.175.36, 123.18.183.92, 40.252.101.91, 152.206.9.27, 108.166.5.31, 223.122.105.128, 151.105.128.180, 27.69.191.139, 134.192.210.104, 61.220.196.1, 172.41.238.243, 131.106.157.133, 94.74.175.216, 103.107.202.3, 143.17.115.197, 209.160.117.48, 145.218.73.187] \\\\u7684 5555 \\\\u7aef\\\\u53e3\\\\u53d1\\\\u8d77TCP\\\\u7aef\\\\u53e3\\\\u626b\\\\u63cf'] \", \"suffer_classify1_id_name\": \"未知\", \"suffer_classify_id\": 50000, \"mining_stage\": 0, \"attack_classify1_id_name\": \"终端\", \"attack_direction\": 1, \"log_ids\": [7089466310673600513], \"hash_id\": \"c6a3b965dc6495476fa9df0d2855d8cc\", \"engine\": \"异常行为引擎\", \"first_time\": 1690214526, \"attack_port\": 32479, \"emergency\": \"important\", \"sub_attack_type\": 0, \"attack_province\": \"未知\", \"suffer_branch_name\": \"\", \"is_read\": 0, \"module_type\": 201, \"invasion_stage\": 2, \"attack_ip\": \"172.131.23.53\", \"suffer_port\": 5555, \"principle\": \"主机扫描其他主机的同一端口，扫描某些主机是否存在某个服务。通过发起tcp连接判断某些主机上面的特定端口是否开放\", \"ioc\": [], \"attack_branch_id\": 0, \"suffer_classify1_id\": 5, \"suffer_asset_id\": 0, \"count\": 113, \"attack_classify1_id\": 2, \"analyze_suggest\": \"\", \"dev_name\": \"SANGFOR STA(44.32.240.53)\", \"attack_asset_id\": 135126, \"asset_direction\": 3, \"created_at\": 1690215302, \"attack_country\": \"未知\", \"misreport\": 0, \"suffer_province\": \"未知\", \"url\": [], \"record_date\": 20230725, \"net_action\": 1, \"attack_branch_name\": \"\", \"hole_ids\": [17820081], \"attack_type_name\": \"端口扫描\", \"linkage_status\": 0}\u0000";
        String pattern=".*module_type_name\\\\\": \\\\\"(.*?)\\\\\",.*status_code\\\\\":(.*?),.*?event_desc.*suffer_ip\\\\\":.*?\\\\\"(.*?)\\\\\",.*?tags\\\\\":.*?\\\\\"(.*)\\\\\",.*?damage.*?attack_ip\\\\\":.*?\\\\\"(.*)\\\\\",.*?suffer_port\\\\\":.*?(\\d*),.*";
        while (pattern.contains("\\\\\"")){
            pattern=pattern.replace("\\\\\"", "\\\"");
        }
        Matcher matcher = Pattern.compile(pattern)
                .matcher(message);
        while (matcher.find()) {
            for (int i = 0; i < matcher.groupCount(); i++) {
                String group = matcher.group(i+1);
                System.out.println(group);
            }
        }

    }
}
