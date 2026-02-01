package com.kdbf.digitalLibrary.adapters.out.persistence;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book")
public class BookJpaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(nullable = false)
  private UUID publicId;
  @Column
  private String title;
  @Column
  private String language;

  @ManyToOne
  @JoinColumn(name = "author_id", nullable = false)
  private AuthorJpaEntity author;

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (id ^ (id >>> 32));
    result = prime * result + ((publicId == null) ? 0 : publicId.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    BookJpaEntity other = (BookJpaEntity) obj;
    if (id != other.id)
      return false;
    if (publicId == null) {
      if (other.publicId != null)
        return false;
    } else if (!publicId.equals(other.publicId))
      return false;
    return true;
  }

}
