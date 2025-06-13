package com.fahllivro.backend.model;

import com.fahllivro.backend.model.enums.StatusTroca;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "trocas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Troca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Usuário que propõe a troca
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user1_id", nullable = false)
    private User user1;

    // Livro oferecido por user1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book1_id", nullable = false)
    private Book book1;

    // Usuário que recebe a proposta
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user2_id", nullable = false)
    private User user2;

    // Livro oferecido por user2
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book2_id", nullable = false)
    private Book book2;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusTroca status;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
