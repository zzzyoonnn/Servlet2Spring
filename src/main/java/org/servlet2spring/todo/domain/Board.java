package org.servlet2spring.todo.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "imageSet")
public class Board extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long bno;

  @Column(length = 500, nullable = false)
  private String title;

  @Column(length = 2000, nullable = false)
  private String content;

  @Column(length = 50, nullable = false)
  private String writer;

  public void change(String title, String content) {
    this.title = title;
    this.content = content;
  }

  @OneToMany(mappedBy = "board",  // BoardImage의 board 변수
          cascade = {CascadeType.ALL},
          fetch = FetchType.LAZY,
          orphanRemoval = true)
  @Builder.Default
  private Set<BoardImage> imageSet = new HashSet<>();

  public void addImage(String uuid, String fileName) {
    BoardImage boardImage = BoardImage.builder()
            .uuid(uuid)
            .fileName(fileName)
            .board(this)
            .ord(imageSet.size())
            .build();

    imageSet.add(boardImage);
  }

  public void clearImages() {
    imageSet.forEach(boardImage -> boardImage.changeBoard(null));

    this.imageSet.clear();
  }


}
