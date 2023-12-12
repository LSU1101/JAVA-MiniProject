import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Vector;

class Player { // 플레이어 클래스 (이름과 점수)
    private String name;
    private int score;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Player(String name, int score) {
        this.name = name; this.score = score;
    }
}

public class GameOverFrame extends JFrame {
    private static Vector<Player> players = new Vector<>();
    private File file = new File("records.txt");

    public GameOverFrame(int score, String playName) {
        recordWrite(playName, score, 1); // 파일에 쓰기
        recordManagement(); // 겹치는 이름 처리
        sortingPlayers(); // 정렬

        setTitle("게임 오버!");
        setSize(200, 200);
        Container c = getContentPane();

        c.setLayout(null);

        JLabel scoreLabel = new JLabel(playName + " | "  + String.valueOf(score) + "점!!");
        scoreLabel.setFont(new Font("Apple SD Gothic Neo", Font.BOLD, 18));
        scoreLabel.setBounds(0, 40, 200, 30);
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        c.add(scoreLabel);

        JLabel comment = new JLabel();
        if (score < 100) {
            comment.setText("아직 배고파!!!");
        } else if (score < 200) {
            comment.setText("배가 아직 안 찼어..");
        } else if (score < 300) {
            comment.setText("이제 좀 배부르다! 고마워!!");
        }
        comment.setForeground(new Color(77, 54, 39));
        comment.setBounds(0, 90, 200, 30);
        comment.setHorizontalAlignment(JLabel.CENTER);
        comment.setFont(new Font("Apple SD Gothic Neo", Font.PLAIN, 20));
        c.add(comment);

        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    // 기록 저장
    private void recordWrite(String playerName, int score, int mode) { // mode 1 = append, mode 0 = reWrite
        try {
            FileWriter fileWriter;
            if (mode == 1) {
                fileWriter = new FileWriter(file, true);
            } else {
                fileWriter = new FileWriter(file, false);
            }
            fileWriter.write(playerName + "," + score + "\n");
            fileWriter.close();
        } catch (Exception e) {
            System.out.println("기록 저장 오류");
        }
    }

    private void recordManagement() {
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String oneLine = "";

            while ((oneLine = bufferedReader.readLine()) != null) {
                String[] nameAndScore = oneLine.split(",");
                String name = nameAndScore[0];
                String score = nameAndScore[1];

                Player player = new Player(name, Integer.parseInt(score));
                players.add(player);
            }

            // 같은 이름이 있는지 검사
            for (int i = 0; i < players.size(); i++) {
                Player player = players.get(i); // 이전 점수
                String name = player.getName();
                int score = player.getScore();

                for (int j = i + 1; j < players.size(); j++) {
                    Player comparisonTarget = players.get(j); // 새로운 점수
                    String newName = comparisonTarget.getName();
                    int newScore = comparisonTarget.getScore();

                    if (name.equals(newName) && score >= newScore) { // 이름이 같고 이전 점수가 더 높다면
                        players.remove(comparisonTarget); // 새로운 것 지워버리기
                        // i--;
                    } else if (name.equals(newName)) { // 새로운 점수가 더 높다면
                        player.setScore(newScore); // 바꿔주고
                        players.remove(comparisonTarget); // 새로운 것 지워버리기
                        i--; // 지워졌으니 한 칸씩 값의 자리가 당겨짐
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("No records File.");
        }
    }

    public void sortingPlayers() {
        String[] tempName = new String[players.size()];
        int[] tempScore = new int[players.size()];

        for (int i = 0; i < players.size(); i++) {
            Player tempPlayer = players.get(i);
            tempName[i] = tempPlayer.getName();
            tempScore[i] = tempPlayer.getScore();
        }

        for (int i = 0; i < players.size(); i++) {
            int maxIndex = i;

            for (int j = i + 1; j < players.size(); j++) {
                if (tempScore[j] > tempScore[i]) {
                    maxIndex = j;
                }
            }

            Player tempPlay = players.get(i);
            int tempScoreInt = tempScore[i];
            tempScore[i] = tempScore[maxIndex];
            players.get(i).setScore(tempScore[maxIndex]);
            tempScore[maxIndex] = tempScoreInt;
            players.get(maxIndex).setScore(tempPlay.getScore());

            String tempNameString = tempName[i];
            tempName[i] = tempName[maxIndex];
            players.get(i).setName(tempName[maxIndex]);
            tempName[maxIndex] = tempNameString;
            players.get(maxIndex).setName(tempPlay.getName());
        }

        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            String name = player.getName();
            int score = player.getScore();
            if (i == 0) {
                recordWrite(name, score, 0);
            } else {
                recordWrite(name, score, 1);
            }
        }
    }
}
